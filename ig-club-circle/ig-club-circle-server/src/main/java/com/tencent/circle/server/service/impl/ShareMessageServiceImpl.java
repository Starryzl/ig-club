package com.tencent.circle.server.service.impl;

import com.tencent.circle.api.common.PageInfo;
import com.tencent.circle.api.common.PageResult;
import com.tencent.circle.api.enums.IsDeletedFlagEnum;
import com.tencent.circle.api.req.GetShareMessageReq;
import com.tencent.circle.api.vo.ShareMessageVO;
import com.tencent.circle.server.config.websocket.IgSocket;
import com.tencent.circle.server.dao.ShareMessageMapper;
import com.tencent.circle.server.entity.po.ShareMessage;
import com.tencent.circle.server.rpc.UserRpc;
import com.tencent.circle.server.service.ShareMessageService;
import com.tencent.circle.server.util.LoginUtil;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ShareMessageServiceImpl extends ServiceImpl<ShareMessageMapper, ShareMessage> implements ShareMessageService {
    @Resource
    private IgSocket igSocket;
    @Resource
    private UserRpc userRpc;

    @Override
    public PageResult<ShareMessageVO> getMessages(GetShareMessageReq req) {

        LambdaQueryWrapper<ShareMessage> query = Wrappers.<ShareMessage>lambdaQuery()
                .eq(ShareMessage::getToId, LoginUtil.getLoginId())
                .eq(ShareMessage::getIsRead, req.getIsRead())
                .eq(ShareMessage::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode())
                .orderByDesc(ShareMessage::getId);
        PageInfo pageInfo = req.getPageInfo();
        Page<ShareMessage> page = new Page<>(pageInfo.getPageNo(), pageInfo.getPageSize());
        Page<ShareMessage> pageRes = super.page(page, query);
        PageResult<ShareMessageVO> result = new PageResult<>();
        List<ShareMessage> records = pageRes.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            List<Long> ids = records.stream().map(ShareMessage::getId).collect(Collectors.toList());
            LambdaUpdateWrapper<ShareMessage> update = Wrappers.<ShareMessage>lambdaUpdate().set(ShareMessage::getIsRead, 1).in(ShareMessage::getId, ids);
            super.update(update);
        }
        List<ShareMessageVO> list = records.stream().map(item -> {
            ShareMessageVO vo = new ShareMessageVO();
            vo.setId(item.getId());
            vo.setContent(JSON.parseObject(item.getContent()));
            vo.setCreatedTime(item.getCreatedTime());
            return vo;
        }).collect(Collectors.toList());
        result.setRecords(list);
        result.setTotal((int) pageRes.getTotal());
        result.setPageSize(pageInfo.getPageSize());
        result.setPageNo(pageInfo.getPageNo());
        return result;

    }

    @Override
    public void comment(String fromId, String toId, Long targetId) {

        JSONObject message = new JSONObject();
        // 1=评论 2=回复
        message.put("msgType", "COMMENT");
        message.put("msg", "评论了你的内容，快来看看把");
        message.put("targetId", targetId);
        ShareMessage shareMessage = new ShareMessage();
        shareMessage.setFromId(fromId);
        shareMessage.setToId(toId);
        shareMessage.setContent(message.toJSONString());
        shareMessage.setIsRead(2);
        shareMessage.setCreatedBy(fromId);
        shareMessage.setCreatedTime(new Date());
        shareMessage.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        IgSocket socket = igSocket.getChickenSocket(toId);
        if (Objects.nonNull(socket)) {
            igSocket.sendMessage(shareMessage.getContent(), socket.getSession());
        }
        super.save(shareMessage);

    }

    @Override
    public void reply(String fromId, String toId, Long targetId) {

        JSONObject message = new JSONObject();
        // 1=评论 2=回复
        message.put("msgType", "COMMENT_REPLY");
        message.put("msg", String.format("%s 回复了你的评论，快来看看把！", userRpc.getUserInfo(fromId).getNickName()));
        message.put("targetId", targetId);
        ShareMessage shareMessage = new ShareMessage();
        shareMessage.setFromId(fromId);
        shareMessage.setToId(toId);
        shareMessage.setContent(message.toJSONString());
        shareMessage.setIsRead(2);
        shareMessage.setCreatedBy(fromId);
        shareMessage.setCreatedTime(new Date());
        shareMessage.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        IgSocket socket = igSocket.getChickenSocket(toId);
        if (Objects.nonNull(socket)) {
            igSocket.sendMessage(shareMessage.getContent(), socket.getSession());
        }
        super.save(shareMessage);

    }

    @Override
    public Boolean unRead() {
        LambdaQueryWrapper<ShareMessage> query = Wrappers.<ShareMessage>lambdaQuery()
                .eq(ShareMessage::getToId, LoginUtil.getLoginId())
                .eq(ShareMessage::getIsRead, 2)
                .eq(ShareMessage::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode());
        return super.count(query) > 0;
    }

}
