package com.tencent.subject.domain.service;

import com.tencent.subject.domain.entity.SubjectCategoryBO;
import com.tencent.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 题目标签领域服务
 */

public interface SubjectLabelDomainService {

    /**
     * 新增标签
     *
     */

    Boolean add(SubjectLabelBO subjectLabelBO);


    /**
     * 更新标签
     *
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     * @param subjectLabelBO
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     * @param subjectLabelBO
     * @return
     */

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
