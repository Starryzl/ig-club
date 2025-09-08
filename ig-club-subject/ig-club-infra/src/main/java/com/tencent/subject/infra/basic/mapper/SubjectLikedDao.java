package com.tencent.subject.infra.basic.mapper;

import com.tencent.subject.infra.basic.entity.SubjectLiked;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题目点赞表 表数据库访问层
 *
 * @author ig
 * @since 2025-08-30 00:04:18
 */
@Repository
public interface SubjectLikedDao extends BaseMapper<SubjectLiked> {
    int insertBatch(@Param("entities") List<SubjectLiked> entities);
}

