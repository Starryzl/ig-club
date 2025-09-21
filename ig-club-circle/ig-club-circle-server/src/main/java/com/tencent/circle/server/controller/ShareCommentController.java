package com.tencent.circle.server.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tencent.circle.api.common.Result;
import com.tencent.circle.api.enums.IsDeletedFlagEnum;
import com.tencent.circle.api.req.*;
import com.tencent.circle.server.entity.po.ShareMoment;
import com.tencent.circle.server.service.ShareCommentReplyService;
import com.tencent.circle.server.service.ShareMomentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/share/comment")
public class ShareCommentController {

    @Resource
    private ShareMomentService shareMomentService;
    @Resource
    private ShareCommentReplyService shareCommentReplyService;

    /**
     * 发布内容
     */
    @PostMapping(value = "/save")
    public Result<Boolean> save(@RequestBody SaveShareCommentReplyReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("发布内容入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(Objects.nonNull(req), "参数不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getReplyType()), "类型不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getMomentId()), "内容ID不能为空！");
            ShareMoment moment = shareMomentService.getById(req.getMomentId());
            Preconditions.checkArgument((Objects.nonNull(moment) && moment.getIsDeleted() != IsDeletedFlagEnum.DELETED.getCode()), "非法内容！");
            Preconditions.checkArgument((Objects.nonNull(req.getContent()) || Objects.nonNull(req.getPicUrlList())), "内容不能为空！");
            Boolean result = shareCommentReplyService.saveComment(req);
            if (log.isInfoEnabled()) {
                log.info("发布内容{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("发布内容异常！错误原因{}", e.getMessage(), e);
            return Result.fail("发布内容异常！");
        }
    }


    /**
     * 删除社圈评论内容
     */
    @PostMapping(value = "/remove")
    public Result<Boolean> remove(@RequestBody RemoveShareCommentReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("删除社圈评论内容入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(Objects.nonNull(req), "参数不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getReplyType()), "类型不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getId()), "内容ID不能为空！");
            Boolean result = shareCommentReplyService.removeComment(req);
            if (log.isInfoEnabled()) {
                log.info("删除社圈评论内容{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("删除社圈评论内容异常！错误原因{}", e.getMessage(), e);
            return Result.fail("删除社圈评论内容异常！");
        }
    }

}
