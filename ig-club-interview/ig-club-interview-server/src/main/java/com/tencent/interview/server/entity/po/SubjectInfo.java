package com.tencent.interview.server.entity.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectInfo implements Serializable {
    private static final long serialVersionUID = -71318372165220898L;

    private String subjectName;

    private String subjectAnswer;

    private String labelName;

    private String categoryName;

}
