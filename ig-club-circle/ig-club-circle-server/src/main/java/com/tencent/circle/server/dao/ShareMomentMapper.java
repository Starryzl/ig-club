package com.tencent.circle.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.circle.server.entity.po.ShareMoment;
import org.apache.ibatis.annotations.Param;

/**
 * 动态信息 Mapper 接口
 */
public interface ShareMomentMapper extends BaseMapper<ShareMoment> {

    void incrReplyCount(@Param("id") Long id, @Param("count") int count);
}
