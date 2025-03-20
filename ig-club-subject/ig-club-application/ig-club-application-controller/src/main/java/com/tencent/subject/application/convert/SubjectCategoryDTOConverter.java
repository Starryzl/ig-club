package com.tencent.subject.application.convert;

import com.tencent.subject.application.dto.SubjectCategoryDTO;
import com.tencent.subject.domain.entity.SubjectCategoryBO;
import com.tencent.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目分类dto转换器
 */
@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryDTO);

    SubjectCategoryBO convertDTOtoCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryDTO convertBoToCategoryDTO(SubjectCategoryBO subjectCategoryBO);

}
