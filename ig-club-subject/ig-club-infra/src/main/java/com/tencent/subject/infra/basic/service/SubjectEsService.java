package com.tencent.subject.infra.basic.service;

import com.tencent.subject.common.entity.PageResult;
import com.tencent.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}
