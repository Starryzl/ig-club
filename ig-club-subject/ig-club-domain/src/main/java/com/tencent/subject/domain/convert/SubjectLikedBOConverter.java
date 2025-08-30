package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectLikedBO;
import com.tencent.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 题目点赞表 bo转换器
 *
 * @author ig
 * @since 2025-08-30 00:04:18
 */
@Mapper
public interface SubjectLikedBOConverter {

    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO);

}
