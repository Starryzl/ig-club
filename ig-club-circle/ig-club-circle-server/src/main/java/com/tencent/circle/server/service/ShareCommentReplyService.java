package com.tencent.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.circle.api.req.*;
import com.tencent.circle.api.vo.ShareCommentReplyVO;
import com.tencent.circle.server.entity.po.ShareCommentReply;

import java.util.List;

/**
 * 评论及回复信息 服务类
 */
public interface ShareCommentReplyService extends IService<ShareCommentReply> {

    Boolean saveComment(SaveShareCommentReplyReq req);

    Boolean removeComment(RemoveShareCommentReq req);

    List<ShareCommentReplyVO> listComment(GetShareCommentReq req);
}
