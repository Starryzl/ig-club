package com.tencent.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.subject.common.entity.PageResult;
import com.tencent.subject.common.enums.IsDeletedFlagEnum;
import com.tencent.subject.common.util.IdWorkerUtil;
import com.tencent.subject.common.util.LoginUtil;
import com.tencent.subject.domain.convert.SubjectInfoConverter;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import com.tencent.subject.domain.entity.SubjectOptionBO;
import com.tencent.subject.domain.handler.subject.SubjectTypeHandler;
import com.tencent.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.tencent.subject.domain.redis.RedisUtil;
import com.tencent.subject.domain.service.SubjectInfoDomainService;
import com.tencent.subject.infra.basic.entity.SubjectInfo;
import com.tencent.subject.infra.basic.entity.SubjectInfoEs;
import com.tencent.subject.infra.basic.entity.SubjectLabel;
import com.tencent.subject.infra.basic.entity.SubjectMapping;
import com.tencent.subject.infra.basic.service.SubjectEsService;
import com.tencent.subject.infra.basic.service.SubjectInfoService;
import com.tencent.subject.infra.basic.service.SubjectLabelService;
import com.tencent.subject.infra.basic.service.SubjectMappingService;
import com.tencent.subject.infra.entity.UserInfo;
import com.tencent.subject.infra.rpc.UserRpc;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectEsService subjectEsService;

    @Resource
    private UserRpc userRpc;

    @Resource
    private RedisUtil redisUtil;

    private static final String RANK_KEY = "subject_rank";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }

        //假设我们都写在主流程里面
        //判断type，单选的调用单选的service,多选的调用多选的
        //会有一大堆的if

        //可以使用 工厂+策略的形式
        //一个工厂 包含4种类型，根据传入的type自动映射选择处理
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        //
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId->{
            labelIds.forEach(labelId->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
        //同步到es
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1,1,1).nextId());
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateUser("Ig");
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectEsService.insert(subjectInfoEs);
        //redis放入zadd计入排行榜
        redisUtil.addScore(RANK_KEY, LoginUtil.getLoginId(),1);

    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
       int start =  (subjectInfoBO.getPageNo()-1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
      int count =  subjectInfoService.countByCondition(subjectInfo,subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());
      if(count==0) {
          return pageResult;
      }
      List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo,subjectInfoBO.getCategoryId(),
              subjectInfoBO.getLabelId(),start,subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoToBO(subjectInfoList);
        subjectInfoBOS.forEach(info->{
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(info.getId());
            List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            List<Long> labelIds = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIds);
            List<String> labelNames = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
            info.setLabelName(labelNames);
        });
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);

        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO,subjectInfo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        //转换
        bo.setLabelName(labelNameList);
        return bo;
    }

    @Override
    public PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setPageNo(subjectInfoBO.getPageNo());
        subjectInfoEs.setPageSize(subjectInfoBO.getPageSize());
        subjectInfoEs.setKeyWord(subjectInfoBO.getKeyWord());
        return subjectEsService.querySubjectList(subjectInfoEs);
    }

    @Override
    public List<SubjectInfoBO> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.rankWithScore(RANK_KEY, 0, 5);
        if(log.isInfoEnabled()){
            log.info("getContributeList.typedTuples:{}",JSON.toJSONString(typedTuples));
        }
      if(CollectionUtils.isEmpty(typedTuples)){
          return Collections.emptyList();
      }

      List<SubjectInfoBO> boList = new LinkedList<>();
      typedTuples.forEach(rank -> {
          SubjectInfoBO subjectInfoBO = new SubjectInfoBO();
          subjectInfoBO.setSubjectCount(rank.getScore().intValue());
          UserInfo userInfo = userRpc.getUserInfo(rank.getValue());
          subjectInfoBO.setCreateUser(userInfo.getNickName());
          subjectInfoBO.setCreateUserAvatar(userInfo.getAvatar());
          boList.add(subjectInfoBO);
      });
      return boList;
    }

}
