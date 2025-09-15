package com.tencent.practice.api.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetPracticeSubjectsReq implements Serializable {
    /**
     * 套题id
     */
    private Long setId;

}
