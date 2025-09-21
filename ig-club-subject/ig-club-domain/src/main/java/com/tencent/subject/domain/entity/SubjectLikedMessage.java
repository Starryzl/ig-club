package com.tencent.subject.domain.entity;

import com.tencent.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 题目点赞消息
 *
 * @author ig
 * @since 2025-08-30 00:04:18
 */
@Data
public class SubjectLikedMessage extends PageInfo implements Serializable {

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;

}

