package com.tencent.subject.application.convert;

import com.tencent.subject.application.dto.SubjectAnswerDTO;
import com.tencent.subject.application.dto.SubjectInfoDTO;
import com.tencent.subject.domain.entity.SubjectAnswerBO;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerDTO convertDTOToBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertListDTOToBO (List<SubjectAnswerDTO> dtoList);

}
