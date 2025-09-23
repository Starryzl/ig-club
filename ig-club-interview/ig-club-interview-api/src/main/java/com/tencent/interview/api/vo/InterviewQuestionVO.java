package com.tencent.interview.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterviewQuestionVO implements Serializable {

    private List<Interview> questionList;

    @Data
    public static class Interview {
        private String labelName;

        private String keyWord;

        private String subjectName;

        private String subjectAnswer;

    }


}
