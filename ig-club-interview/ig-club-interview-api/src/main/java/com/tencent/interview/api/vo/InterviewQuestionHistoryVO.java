package com.tencent.interview.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 面试汇总记录表(InterviewHistory)实体类
 */
@Data
public class InterviewQuestionHistoryVO implements Serializable {

    private static final long serialVersionUID = -60560874889446691L;

    /**
     * 均分
     */
    private Double score;
    /**
     * 面试关键字
     */
    private String keyWords;
    /**
     * 问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 用户答案
     */
    private String userAnswer;

}
