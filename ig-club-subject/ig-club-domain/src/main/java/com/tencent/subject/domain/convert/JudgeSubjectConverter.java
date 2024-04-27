package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectAnswerBO;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import com.tencent.subject.domain.entity.SubjectOptionBO;
import com.tencent.subject.infra.basic.entity.SubjectBrief;
import com.tencent.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JudgeSubjectConverter {
    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectJudge> subjectJudgeList);



}
