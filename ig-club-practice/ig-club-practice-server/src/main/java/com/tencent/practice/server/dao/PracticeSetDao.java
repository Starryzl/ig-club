package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.PracticeSetPO;

public interface PracticeSetDao {

    /**
     * 新增套题
     */
    int add(PracticeSetPO po);

    PracticeSetPO selectById(Long setId);

    void updateHeat(Long setId);
}

