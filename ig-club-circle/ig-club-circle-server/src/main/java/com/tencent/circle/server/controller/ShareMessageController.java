package com.tencent.circle.server.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.circle.api.common.PageResult;
import com.tencent.circle.api.common.Result;
import com.tencent.circle.api.req.GetShareMessageReq;
import com.tencent.circle.api.vo.ShareMessageVO;
import com.tencent.circle.server.service.ShareMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/share/message")
public class ShareMessageController {

    @Resource
    private ShareMessageService shareMessageService;

    /**
     * 分页查询消息
     */
    @GetMapping(value = "/unRead")
    public Result<Boolean> unRead() {
        try {
            return Result.ok(shareMessageService.unRead());
        } catch (Exception e) {
            log.error("消息异常！错误原因{}", e.getMessage(), e);
            return Result.fail("消息异常！");
        }
    }

    /**
     * 分页查询消息
     */
    @PostMapping(value = "/getMessages")
    public Result<PageResult<ShareMessageVO>> getMessages(@RequestBody GetShareMessageReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("消息入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(!Objects.isNull(req), "参数不能为空！");
            PageResult<ShareMessageVO> result = shareMessageService.getMessages(req);
            if (log.isInfoEnabled()) {
                log.info("消息出参{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("消息异常！错误原因{}", e.getMessage(), e);
            return Result.fail("消息异常！");
        }
    }



}
