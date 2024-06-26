package com.tencent.subject.application.convert;

import com.tencent.subject.application.dto.SubjectCategoryDTO;
import com.tencent.subject.domain.entity.SubjectCategoryBO;
import com.tencent.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryDTO);


    SubjectCategoryBO convertDTOtoCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
}
