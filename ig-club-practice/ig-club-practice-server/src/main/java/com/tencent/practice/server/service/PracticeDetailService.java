package com.tencent.practice.server.service;

import com.tencent.practice.api.req.SubmitPracticeDetailReq;
import com.tencent.practice.api.req.SubmitSubjectDetailReq;

public interface PracticeDetailService {

    /**
     * 提交练题情况
     */
    Boolean submit(SubmitPracticeDetailReq req);

    /**
     * 练习提交题目
     */
    Boolean submitSubject(SubmitSubjectDetailReq req);
}
