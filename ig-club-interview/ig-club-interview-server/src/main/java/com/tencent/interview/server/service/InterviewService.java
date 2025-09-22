package com.tencent.interview.server.service;

import com.tencent.interview.api.req.InterviewReq;
import com.tencent.interview.api.vo.InterviewVO;

public interface InterviewService {

    InterviewVO analyse(InterviewReq req);
}
