package com.tencent.interview.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.interview.api.enums.IsDeletedFlagEnum;
import com.tencent.interview.api.req.InterviewSubmitReq;
import com.tencent.interview.api.vo.InterviewResultVO;
import com.tencent.interview.server.dao.InterviewHistoryMapper;
import com.tencent.interview.server.dao.InterviewQuestionHistoryMapper;
import com.tencent.interview.server.entity.po.InterviewHistory;
import com.tencent.interview.server.entity.po.InterviewQuestionHistory;
import com.tencent.interview.server.service.InterviewHistoryService;
import com.tencent.interview.server.util.LoginUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 面试汇总记录表(InterviewHistory)表服务实现类
 */
@Service("interviewHistoryService")
public class InterviewHistoryServiceImpl extends ServiceImpl<InterviewHistoryMapper, InterviewHistory> implements InterviewHistoryService {
    @Resource
    private InterviewHistoryMapper interviewHistoryMapper;
    @Resource
    private InterviewQuestionHistoryMapper interviewQuestionHistoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logInterview(InterviewSubmitReq req, InterviewResultVO submit) {

        InterviewHistory history = new InterviewHistory();
        history.setAvgScore(submit.getAvgScore());
        String keyWords = req.getQuestionList().stream().map(InterviewSubmitReq.Submit::getLabelName).distinct().collect(Collectors.joining("、"));
        history.setKeyWords(keyWords);
        history.setTip(submit.getTips());
        history.setInterviewUrl(req.getInterviewUrl());
        history.setCreatedBy(LoginUtil.getLoginId());
        history.setCreatedTime(new Date());
        history.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        interviewHistoryMapper.insert(history);
        List<InterviewQuestionHistory> histories = req.getQuestionList().stream().map(item -> {
            InterviewQuestionHistory questionHistory = new InterviewQuestionHistory();
            questionHistory.setInterviewId(history.getId());
            questionHistory.setScore(item.getUserScore());
            questionHistory.setKeyWords(item.getLabelName());
            questionHistory.setQuestion(item.getSubjectName());
            questionHistory.setAnswer(item.getSubjectAnswer());
            questionHistory.setUserAnswer(item.getUserAnswer());
            questionHistory.setCreatedBy(history.getCreatedBy());
            questionHistory.setCreatedTime(history.getCreatedTime());
            questionHistory.setIsDeleted(history.getIsDeleted());
            return questionHistory;
        }).collect(Collectors.toList());
        interviewQuestionHistoryMapper.insertBatch(histories);

    }

}
