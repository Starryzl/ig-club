package com.tencent.interview.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.interview.server.entity.po.InterviewQuestionHistory;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * 面试题目记录表(InterviewQuestionHistory)表数据库访问层
 */
public interface InterviewQuestionHistoryMapper extends BaseMapper<InterviewQuestionHistory> {

    /**
     * 通过ID查询单条数据
     */
    InterviewQuestionHistory queryById(Long id);

    /**
     * 查询指定行数据
     */
    List<InterviewQuestionHistory> queryAllByLimit(InterviewQuestionHistory interviewQuestionHistory, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     */
    long count(InterviewQuestionHistory interviewQuestionHistory);

    /**
     * 新增数据
     */
    int insert(InterviewQuestionHistory interviewQuestionHistory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     */
    int insertBatch(@Param("entities") List<InterviewQuestionHistory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     */
    int insertOrUpdateBatch(@Param("entities") List<InterviewQuestionHistory> entities);

    /**
     * 修改数据
     */
    int update(InterviewQuestionHistory interviewQuestionHistory);

    /**
     * 通过主键删除数据
     */
    int deleteById(Long id);


}
