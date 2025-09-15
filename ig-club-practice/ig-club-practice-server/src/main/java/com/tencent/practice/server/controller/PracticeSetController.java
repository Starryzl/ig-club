package com.tencent.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.tencent.practice.api.common.Result;
import com.tencent.practice.api.vo.SpecialPracticeVO;
import com.tencent.practice.server.service.PracticeSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 练习套卷controller
 */
@RestController
@RequestMapping("/practice/set/")
@Slf4j
public class PracticeSetController {
    @Resource
    private PracticeSetService practiceSetService;

    @RequestMapping("getSpecialPracticeContent")
    public Result<List<SpecialPracticeVO>> getSpecialPracticeContent() {
        try {
            List<SpecialPracticeVO> result = practiceSetService.getSpecialPracticeContent();
            if (log.isInfoEnabled()) {
                log.info("getSpecialPracticeContent.result:{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (Exception e) {
            log.error("getSpecialPracticeContent.error:{}", e.getMessage(), e);
            return Result.fail("获取专项练习内容失败");
        }

    }

}
