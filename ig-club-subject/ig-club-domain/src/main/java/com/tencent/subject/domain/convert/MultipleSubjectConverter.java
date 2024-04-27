package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectAnswerBO;
import com.tencent.subject.infra.basic.entity.SubjectJudge;
import com.tencent.subject.infra.basic.entity.SubjectMultiple;
import com.tencent.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConverter {
    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectMultiple> subjectMultipleList);



}
