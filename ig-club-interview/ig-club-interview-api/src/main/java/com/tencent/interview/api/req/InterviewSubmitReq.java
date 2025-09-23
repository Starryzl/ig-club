package com.tencent.interview.api.req;

import com.tencent.interview.api.enums.EngineEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterviewSubmitReq implements Serializable {

    private String engine = EngineEnum.IGLOCAL.name();

    private String interviewUrl;

    private List<Submit> questionList;

    @Data
    public static class Submit {

        private String labelName;

        private String subjectName;

        private String subjectAnswer;

        private String userAnswer;

        private Double userScore;

    }


}
