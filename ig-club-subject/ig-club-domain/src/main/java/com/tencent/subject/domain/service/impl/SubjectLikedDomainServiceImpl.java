package com.tencent.subject.domain.service.impl;

import com.tencent.subject.common.enums.IsDeletedFlagEnum;
import com.tencent.subject.common.enums.SubjectLikedStatusEnum;
import com.tencent.subject.domain.convert.SubjectLikedBOConverter;
import com.tencent.subject.domain.entity.SubjectLikedBO;
import com.tencent.subject.domain.redis.RedisUtil;
import com.tencent.subject.domain.service.SubjectLikedDomainService;
import com.tencent.subject.infra.basic.entity.SubjectLiked;
import com.tencent.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 题目点赞表 领域service实现了
 *
 * @author ig
 * @since 2025-08-30 00:04:18
 */
@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private RedisUtil redisUtil;

    private static final String SUBJECT_LIKED_KEY = "subject.liked";

    private static final String SUBJECT_LIKED_COUNT_KEY = "subject.liked.count";

    private static final String SUBJECT_LIKED_DETAIL_KEY = "subject.liked.detail";
    @Override
    public void add(SubjectLikedBO subjectLikedBO) {
        Long subjectId = subjectLikedBO.getSubjectId();
        String likeUserId = subjectLikedBO.getLikeUserId();
        Integer status = subjectLikedBO.getStatus();
        String hashKey = buildSubjectLikedId(subjectId.toString(), likeUserId);
        redisUtil.putHash(SUBJECT_LIKED_KEY,hashKey,status);
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "." + likeUserId;
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
        if(SubjectLikedStatusEnum.LIKED.getCode() == status){
            redisUtil.increment(countKey,1);
            redisUtil.set(detailKey,"1");
        }else {
            Integer count = redisUtil.getInt(countKey);
            if(Objects.isNull(count) || count <=0){
                return;
            }
            redisUtil.increment(countKey,-1);
            redisUtil.del(detailKey);
        }
    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "." + userId;
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;
            Integer count = redisUtil.getInt(countKey);
            if(Objects.isNull(count) || count <=0){
                return 0;
            }
        return redisUtil.getInt(countKey);
    }

    public String buildSubjectLikedId(String subjectId,String userId){
        return subjectId + ":" + userId;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setId(subjectLikedBO.getId());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLikedService.update(subjectLiked) > 0;
    }

}
