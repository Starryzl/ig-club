package com.tencent.subject.domain.service;

import com.tencent.subject.common.entity.PageResult;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import com.tencent.subject.domain.entity.SubjectLabelBO;
import com.tencent.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * 题目领域服务
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    /**
     * 全文检索
     */
    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO);
}
