package com.tencent.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.circle.api.req.*;
import com.tencent.circle.server.entity.po.ShareCommentReply;

/**
 * 评论及回复信息 服务类
 */
public interface ShareCommentReplyService extends IService<ShareCommentReply> {

    Boolean saveComment(SaveShareCommentReplyReq req);

    Boolean removeComment(RemoveShareCommentReq req);

}
