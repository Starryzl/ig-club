package com.tencent.subject.domain.entity;

import com.tencent.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目dto
 *
 * @author makejava
 * @since 2024-04-21 08:58:58
 */
@Data
public class SubjectOptionBO extends PageInfo implements Serializable {

    /**
     * 题目答案
     */
    private String subjectAnswer;


    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;



}

