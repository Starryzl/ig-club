package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.SubjectLabelPO;

/**
 * 题目标签表(SubjectLabel)表数据库访问层
 *
 */
public interface SubjectLabelDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabelPO queryById(Long id);

}

