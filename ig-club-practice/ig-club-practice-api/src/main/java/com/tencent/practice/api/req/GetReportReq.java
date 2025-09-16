package com.tencent.practice.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetReportReq implements Serializable {
    /**
     * 练习id
     */
    private Long practiceId;

}
