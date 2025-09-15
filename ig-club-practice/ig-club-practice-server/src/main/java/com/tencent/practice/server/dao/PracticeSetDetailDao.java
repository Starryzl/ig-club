package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.PracticeSetDetailPO;

import java.util.List;

public interface PracticeSetDetailDao {
    /**
     * 新增套题
     */
    int add(PracticeSetDetailPO po);

    List<PracticeSetDetailPO> selectBySetId(Long setId);
}
