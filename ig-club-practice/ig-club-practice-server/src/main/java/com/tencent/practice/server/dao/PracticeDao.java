package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.PracticePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PracticeDao {

    /**
     * 根据练题id获取详情
     */
    PracticePO selectById(Long id);

    /**
     * 新增
     */
    int insert(PracticePO practicePO);

    /**
     * 更新练习详情
     */
    int update(PracticePO practicePO);

    /**
     * 删除练习
     */
    void deleteById(Long practiceId);

    /**
     * 查询未完成的练习题数量
     */
    Integer getUnCompleteCount(String loginId);

    /**
     * 查询未完成的练习题
     */
    List<PracticePO> getUnCompleteList(@Param("loginId") String loginId,
                                       @Param("limit") int limit,
                                       @Param("offset") int pageSize);

}
