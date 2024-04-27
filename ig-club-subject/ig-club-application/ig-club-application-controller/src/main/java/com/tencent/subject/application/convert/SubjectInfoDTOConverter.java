package com.tencent.subject.application.convert;

import com.tencent.subject.application.dto.SubjectInfoDTO;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);


    SubjectInfoDTO convertBOToDTO(SubjectInfoBO subjectInfoBO);


}
