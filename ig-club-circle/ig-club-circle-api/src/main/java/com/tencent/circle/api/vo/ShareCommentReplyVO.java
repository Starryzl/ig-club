package com.tencent.circle.api.vo;

import com.tencent.circle.api.common.TreeNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ShareCommentReplyVO extends TreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 原始动态ID
     */
    private Long momentId;

    /**
     * 回复类型 1评论 2回复
     */
    private Integer replyType;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片内容
     */
    private List<String> picUrlList;

    private String fromId;

    private String toId;

    private Long parentId;

    private String userName;

    private String avatar;

    private long createdTime;

    @Override
    public Long getNodeId() {
        return id;
    }

    @Override
    public Long getNodePId() {
        return parentId;
    }


}
