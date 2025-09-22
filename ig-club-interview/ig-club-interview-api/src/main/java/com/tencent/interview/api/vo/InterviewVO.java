package com.tencent.interview.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterviewVO implements Serializable {

    private List<Interview> questionList;

    @Data
    public static class Interview {
        private String keyWord;
        private Long categoryId;
        private Long labelId;
    }

}
