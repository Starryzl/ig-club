package com.tencent.interview.server.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sensitive_words")
public class SensitiveWords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容
     */
    private String words;

    /**
     * 1=黑名单 2=白名单
     */
    private Integer type;

    /**
     * 是否被删除 0为删除 1已删除
     */
    private Integer isDeleted;

}
