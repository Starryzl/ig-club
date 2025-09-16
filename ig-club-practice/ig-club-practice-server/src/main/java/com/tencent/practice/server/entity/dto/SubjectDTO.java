package com.tencent.practice.server.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDTO implements Serializable {

    /**
     * 题目id
     */
    private Long id;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目类型
     */
    private Integer subjectType;

}
