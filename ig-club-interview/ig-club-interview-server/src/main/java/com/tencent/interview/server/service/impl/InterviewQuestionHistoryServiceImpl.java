package com.tencent.interview.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.interview.api.enums.IsDeletedFlagEnum;
import com.tencent.interview.api.vo.InterviewQuestionHistoryVO;
import com.tencent.interview.server.dao.InterviewQuestionHistoryMapper;
import com.tencent.interview.server.entity.po.InterviewQuestionHistory;
import com.tencent.interview.server.service.InterviewQuestionHistoryService;
import com.tencent.interview.server.util.LoginUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 面试题目记录表(InterviewQuestionHistory)表服务实现类
 */
@Service("interviewQuestionHistoryService")
public class InterviewQuestionHistoryServiceImpl extends ServiceImpl<InterviewQuestionHistoryMapper, InterviewQuestionHistory> implements InterviewQuestionHistoryService {

    @Override
    public List<InterviewQuestionHistoryVO> detail(Long id) {
        LambdaQueryWrapper<InterviewQuestionHistory> query = Wrappers.<InterviewQuestionHistory>lambdaQuery()
                .eq(InterviewQuestionHistory::getInterviewId, id)
                .eq(InterviewQuestionHistory::getCreatedBy, LoginUtil.getLoginId())
                .eq(InterviewQuestionHistory::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode());
        return super.list(query).stream().map(item -> {
            InterviewQuestionHistoryVO vo = new InterviewQuestionHistoryVO();
            vo.setScore(item.getScore());
            vo.setKeyWords(item.getKeyWords());
            vo.setQuestion(item.getQuestion());
            vo.setAnswer(item.getAnswer());
            vo.setUserAnswer(item.getUserAnswer());
            return vo;
        }).collect(Collectors.toList());
    }

}
