package com.tencent.interview.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.interview.api.common.PageResult;
import com.tencent.interview.api.req.InterviewHistoryReq;
import com.tencent.interview.api.req.InterviewSubmitReq;
import com.tencent.interview.api.vo.InterviewHistoryVO;
import com.tencent.interview.api.vo.InterviewResultVO;
import com.tencent.interview.server.entity.po.InterviewHistory;

/**
 * 面试汇总记录表(InterviewHistory)表服务接口
 */
public interface InterviewHistoryService extends IService<InterviewHistory> {
    void logInterview(InterviewSubmitReq req, InterviewResultVO submit);

    PageResult<InterviewHistoryVO> getHistory(InterviewHistoryReq req);
}
