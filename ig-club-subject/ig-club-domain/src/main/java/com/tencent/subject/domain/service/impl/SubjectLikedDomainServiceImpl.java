package com.tencent.subject.domain.service.impl;

import com.tencent.subject.common.enums.IsDeletedFlagEnum;
import com.tencent.subject.domain.convert.SubjectLikedBOConverter;
import com.tencent.subject.domain.entity.SubjectLikedBO;
import com.tencent.subject.domain.service.SubjectLikedDomainService;
import com.tencent.subject.infra.basic.entity.SubjectLiked;
import com.tencent.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Boolean add(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        return subjectLikedService.insert(subjectLiked) > 0;
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
