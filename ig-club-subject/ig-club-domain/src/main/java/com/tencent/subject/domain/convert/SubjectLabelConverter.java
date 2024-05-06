package com.tencent.subject.domain.convert;

import com.tencent.subject.domain.entity.SubjectCategoryBO;
import com.tencent.subject.domain.entity.SubjectLabelBO;
import com.tencent.subject.infra.basic.entity.SubjectCategory;
import com.tencent.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertLabelToBoList(List<SubjectLabel> subjectLabelList);



}
