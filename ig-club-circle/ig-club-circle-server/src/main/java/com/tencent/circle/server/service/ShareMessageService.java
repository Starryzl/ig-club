package com.tencent.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.circle.api.common.PageResult;
import com.tencent.circle.api.req.GetShareMessageReq;
import com.tencent.circle.api.vo.ShareMessageVO;
import com.tencent.circle.server.entity.po.ShareMessage;

/**
 * 消息表 服务类
 */
public interface ShareMessageService extends IService<ShareMessage> {

    PageResult<ShareMessageVO> getMessages(GetShareMessageReq req);

    void comment(String fromId, String toId, Long targetId);

    void reply(String fromId, String toId, Long targetId);

    Boolean unRead();

}
