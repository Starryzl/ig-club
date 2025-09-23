package com.tencent.interview.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.interview.server.entity.po.InterviewHistory;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * 面试汇总记录表(InterviewHistory)表数据库访问层
 */
public interface InterviewHistoryMapper extends BaseMapper<InterviewHistory> {
    /**
     * 通过ID查询单条数据
     */
    InterviewHistory queryById(Long id);

    /**
     * 查询指定行数据
     */
    List<InterviewHistory> queryAllByLimit(InterviewHistory interviewHistory, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     */
    long count(InterviewHistory interviewHistory);

    /**
     * 新增数据
     */
    int insert(InterviewHistory interviewHistory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     */
    int insertBatch(@Param("entities") List<InterviewHistory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     */
    int insertOrUpdateBatch(@Param("entities") List<InterviewHistory> entities);

    /**
     * 修改数据
     */
    int update(InterviewHistory interviewHistory);

    /**
     * 通过主键删除数据
     */
    int deleteById(Long id);

}
