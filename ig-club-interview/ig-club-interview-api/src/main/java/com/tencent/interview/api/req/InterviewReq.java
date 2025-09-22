package com.tencent.interview.api.req;

import com.tencent.interview.api.enums.EngineEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class InterviewReq implements Serializable {
    private String url;

    private String engine = EngineEnum.IGLOCAL.name();

}
