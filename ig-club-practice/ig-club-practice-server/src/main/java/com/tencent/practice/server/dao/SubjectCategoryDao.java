package com.tencent.practice.server.dao;


import com.tencent.practice.server.entity.dto.CategoryDTO;
import com.tencent.practice.server.entity.po.CategoryPO;
import com.tencent.practice.server.entity.po.PrimaryCategoryPO;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表数据库访问层
 */
public interface SubjectCategoryDao {

    List<PrimaryCategoryPO> getPrimaryCategory(CategoryDTO categoryDTO);

    CategoryPO selectById(Long id);

    List<CategoryPO> selectList(CategoryDTO categoryDTOTemp);
}

