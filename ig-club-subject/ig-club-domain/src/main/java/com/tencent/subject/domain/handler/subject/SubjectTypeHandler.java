package com.tencent.subject.domain.handler.subject;

import com.tencent.subject.common.enums.SubjectInfoTypeEnum;
import com.tencent.subject.domain.entity.SubjectInfoBO;
import com.tencent.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    /**
     * 枚举身份的识别
     * @return
     */

    SubjectInfoTypeEnum getHandlerType();


    /**
     * 实际的题目的插入
     * @param
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 实际的题目的插入
     * @param
     */
    SubjectOptionBO query(long subjectId);
}
