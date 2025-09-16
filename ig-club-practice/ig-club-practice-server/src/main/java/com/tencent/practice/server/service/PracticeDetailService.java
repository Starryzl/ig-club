package com.tencent.practice.server.service;

import com.tencent.practice.api.req.GetScoreDetailReq;
import com.tencent.practice.api.req.GetSubjectDetailReq;
import com.tencent.practice.api.req.SubmitPracticeDetailReq;
import com.tencent.practice.api.req.SubmitSubjectDetailReq;
import com.tencent.practice.api.vo.ScoreDetailVO;
import com.tencent.practice.api.vo.SubjectDetailVO;

import java.util.List;

public interface PracticeDetailService {

    /**
     * 提交练题情况
     */
    Boolean submit(SubmitPracticeDetailReq req);

    /**
     * 练习提交题目
     */
    Boolean submitSubject(SubmitSubjectDetailReq req);

    /**
     * 每题得分详情
     */
    List<ScoreDetailVO> getScoreDetail(GetScoreDetailReq req);

    /**
     * 获得答案详情
     */
    SubjectDetailVO getSubjectDetail(GetSubjectDetailReq req);
}
