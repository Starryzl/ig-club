package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectAnswerBO;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import com.tencent.subject.infra.basic.entity.SubjectBrief;
import com.tencent.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConverter {
    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);



}
