package com.tencent.interview.server.service;

import com.tencent.interview.api.enums.EngineEnum;
import com.tencent.interview.api.vo.InterviewVO;

import java.util.List;

/**
 * 面试引擎
 */
public interface InterviewEngine {

    /**
     * 引擎类型
     */
    EngineEnum engineType();

    /**
     * 通过简历关键字获取面试关键字
     */
    InterviewVO analyse(List<String> KeyWords);

}
