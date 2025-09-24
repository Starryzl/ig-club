package com.tencent.interview.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.interview.api.enums.EngineEnum;
import com.tencent.interview.api.req.InterviewSubmitReq;
import com.tencent.interview.api.req.StartReq;
import com.tencent.interview.api.vo.InterviewQuestionVO;
import com.tencent.interview.api.vo.InterviewResultVO;
import com.tencent.interview.api.vo.InterviewVO;
import com.tencent.interview.server.service.InterviewEngine;
import com.tencent.interview.server.util.EvaluateUtils;
import com.tencent.interview.server.util.HttpUtils;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
@Slf4j
@SuppressWarnings("all")
public class AlBLInterviewEngine implements InterviewEngine {

    private static final String apiKey = "sk-XXXXXX";

    @Override
    public EngineEnum engineType() {
        return EngineEnum.ALI_BL;
    }

    @Override
    public InterviewVO analyse(List<String> KeyWords) {
        InterviewVO vo = new InterviewVO();
        List<InterviewVO.Interview> questionList = KeyWords.stream().map(item -> {
            InterviewVO.Interview interview = new InterviewVO.Interview();
            interview.setKeyWord(item);
            interview.setCategoryId(-1L);
            interview.setLabelId(-1L);
            return interview;
        }).collect(Collectors.toList());
        vo.setQuestionList(questionList);
        return vo;
    }

    @Override
    public InterviewResultVO submit(InterviewSubmitReq req) {

        long start = System.currentTimeMillis();
        List<CompletableFuture<InterviewSubmitReq.Submit>> list = req.getQuestionList().stream().
                map(keyword -> CompletableFuture.supplyAsync(() -> buildInterviewScore(keyword)))
                .collect(Collectors.toList());
        List<InterviewSubmitReq.Submit> interviews = new ArrayList<>();
        list.forEach(future -> {
            try {
                if (Objects.nonNull(future)) {
                    InterviewSubmitReq.Submit interview = future.get();
                    interviews.add(interview);
                }
            } catch (Exception e) {
                log.error("buildInterview.get.error", e);
            }
        });
        req.setQuestionList(interviews);
        String tips = interviews.stream().map(item -> {
            String keyWord = item.getLabelName();
            String evaluate = EvaluateUtils.evaluate(item.getUserScore());
            return String.format(evaluate, keyWord);
        }).distinct().collect(Collectors.joining(";"));
        List<InterviewSubmitReq.Submit> submits = req.getQuestionList();
        double total = 0;
        int validCount = 0;
        for (InterviewSubmitReq.Submit item : submits) {
            if (!Double.isNaN(item.getUserScore())) {
                total += item.getUserScore();
                validCount++;
            }
        }
        double avg = validCount > 0 ? total / validCount : 0;
        String avtTips = EvaluateUtils.avgEvaluate(avg);
        InterviewResultVO vo = new InterviewResultVO();
        vo.setAvgScore(avg);
        vo.setTips(tips);
        vo.setAvgTips(avtTips);
        log.info("submit total cost {}", System.currentTimeMillis() - start);
        return vo;

    }

    @Override
    public InterviewQuestionVO start(StartReq req) {

        long start = System.currentTimeMillis();
        List<String> keywords = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            StartReq.Key key = req.getQuestionList().get(new Random().nextInt(req.getQuestionList().size()));
            keywords.add(key.getKeyWord());
        }
        List<CompletableFuture<InterviewQuestionVO.Interview>> list = keywords.stream().
                map(keyword -> CompletableFuture.supplyAsync(() -> buildInterview(keyword)))
                .collect(Collectors.toList());
        List<InterviewQuestionVO.Interview> interviews = new ArrayList<>();
        list.forEach(future -> {
            try {
                if (Objects.nonNull(future)) {
                    InterviewQuestionVO.Interview interview = future.get();
                    interviews.add(interview);
                }
            } catch (Exception e) {
                log.error("buildInterview.get.error", e);
            }
        });
        InterviewQuestionVO vo = new InterviewQuestionVO();
        vo.setQuestionList(interviews);
        log.info("start total cost {}", System.currentTimeMillis() - start);
        return vo;

    }


    private static InterviewSubmitReq.Submit buildInterviewScore(InterviewSubmitReq.Submit submit) {
        long start = System.currentTimeMillis();
        Map<String, Object> reqMap = new HashMap<>();
        JSONObject jsonData = new JSONObject();
        jsonData.put("model", "qwen-flash");
        JSONObject input = new JSONObject();
        JSONObject message2 = new JSONObject();
        message2.put("role", "user");
        String keyword = String.format("题目:%s,答案:%s", submit.getSubjectName(), submit.getUserAnswer());
        String subject = String.format("根据题目和答案 %s ;评一个分数0-5分及参考答案并按照数据结{\"userScore\":\"用户分数\",\"subjectAnswer\":\"参考答案\"}构返json数据", keyword);
        log.info("buildInterview {}", subject);
        message2.put("content", subject);
        input.put("messages", new JSONObject[]{message2});
        jsonData.put("input", input);
        jsonData.put("parameters", new JSONObject());

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + apiKey);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-DashScope-SSE", "enable");

        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String body = HttpUtils.executePost(url, jsonData.toJSONString(), headerMap);
        // fix：检查API是否返回错误
        if (body.contains("InvalidApiKey") || body.contains("No API-key provided.")) {
            log.error("API key is invalid or not provided. API response: {}", body);
            // 返回默认评分（0分）避免后续计算失败
            InterviewSubmitReq.Submit result = new InterviewSubmitReq.Submit();
            result.setUserScore(0.0);
            result.setLabelName(submit.getLabelName());
            result.setSubjectName(submit.getSubjectName());
            result.setUserAnswer(submit.getUserAnswer());
            return result;
        }
        // 输出响应结果
        int index = body.lastIndexOf("data:");
        String substring = body.substring(index + 5, body.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(substring);
        String text = jsonObject.getJSONObject("output").getString("text");
        int jsonIndex = text.lastIndexOf("```json");
        String json = text.substring(jsonIndex + 7, text.lastIndexOf("```"));
        InterviewSubmitReq.Submit interviews = JSONObject.parseObject(json, InterviewSubmitReq.Submit.class);
        interviews.setLabelName(submit.getLabelName());
        interviews.setSubjectName(submit.getSubjectName());
        interviews.setUserAnswer(submit.getUserAnswer());
        log.info("cost {} data:{}", System.currentTimeMillis() - start, JSON.toJSONString(interviews));
        return interviews;
    }

    private static InterviewQuestionVO.Interview buildInterview(String keyword) {
        long start = System.currentTimeMillis();
        JSONObject jsonData = new JSONObject();
        jsonData.put("model", "qwen1.5-110b-chat");
        JSONObject input = new JSONObject();
        JSONObject message2 = new JSONObject();
        message2.put("role", "user");
        String subject = String.format("根据以下关键字生成1道面试题和标签 %s 并按照数据结{\"labelName\":\"分类名称\",\"subjectName\":\"题目\"}构返json数据", keyword);
        log.info("buildInterview {}", subject);
        message2.put("content", subject);
        input.put("messages", new JSONObject[]{message2});
        jsonData.put("input", input);
        jsonData.put("parameters", new JSONObject());


        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + apiKey);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-DashScope-SSE", "enable");

        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
        String body = HttpUtils.executePost(url, jsonData.toJSONString(), headerMap);

        // 输出响应结果
        int index = body.lastIndexOf("data:");
        String substring = body.substring(index + 5, body.length() - 1);
        JSONObject jsonObject = JSONObject.parseObject(substring);
        String text = jsonObject.getJSONObject("output").getString("text");
        int jsonIndex = text.lastIndexOf("```json");
        String json = text.substring(jsonIndex + 7, text.lastIndexOf("```"));
        InterviewQuestionVO.Interview interviews = JSONObject.parseObject(json, InterviewQuestionVO.Interview.class);
        log.info("cost {} data:{}", System.currentTimeMillis() - start, JSON.toJSONString(interviews));
        return interviews;
    }

}
