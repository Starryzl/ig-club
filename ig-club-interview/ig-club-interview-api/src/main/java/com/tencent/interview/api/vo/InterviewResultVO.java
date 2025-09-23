package com.tencent.interview.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InterviewResultVO implements Serializable {

    private Double avgScore;

    private String tips;

    private String avgTips;

}
