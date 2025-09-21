package com.tencent.circle.server.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("share_circle")
public class ShareCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 圈子ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID,-1为大类
     */
    private Long parentId;

    /**
     * 圈子名称
     */
    private String circleName;

    /**
     * 圈子图片
     */
    private String icon;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否被删除 0为删除 1已删除
     */
    private Integer isDeleted;


}
