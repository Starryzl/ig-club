package com.tencent.interview.server.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.auth.entity.Result;
import com.tencent.interview.api.req.InterviewReq;
import com.tencent.interview.api.req.StartReq;
import com.tencent.interview.api.vo.InterviewQuestionVO;
import com.tencent.interview.api.vo.InterviewVO;
import com.tencent.interview.server.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Resource
    private InterviewService interviewService;

    /**
     * 分析简历
     */
    @PostMapping(value = "/analyse")
    public Result<InterviewVO> analyse(@RequestBody InterviewReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("分析简历入参{}", JSON.toJSON(req));
            }
            Preconditions.checkArgument(!Objects.isNull(req), "参数不能为空！");
            Preconditions.checkArgument(!Objects.isNull(req.getEngine()), "引擎不能为空！");
            Preconditions.checkArgument(!Objects.isNull(req.getUrl()), "简历不能为空！");
            return Result.ok(interviewService.analyse(req));
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("分析简历异常！错误原因{}", e.getMessage(), e);
            return Result.fail("分析简历异常！");
        }
    }
    /**
     * 开始面试
     */
    @PostMapping(value = "/start")
    public Result<InterviewQuestionVO> start(@RequestBody StartReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("开始面试入参{}", JSON.toJSON(req));
            }
            Preconditions.checkArgument(!Objects.isNull(req), "参数不能为空！");
            Preconditions.checkArgument(!Objects.isNull(req.getEngine()), "引擎不能为空！");
            Preconditions.checkArgument(!Objects.isNull(req.getQuestionList()), "关键字不能为空！");
            return Result.ok(interviewService.start(req));
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("开始面试异常！错误原因{}", e.getMessage(), e);
            return Result.fail("开始面试异常！");
        }
    }


}
