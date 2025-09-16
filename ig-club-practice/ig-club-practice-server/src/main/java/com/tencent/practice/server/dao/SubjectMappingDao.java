package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.LabelCountPO;
import com.tencent.practice.server.entity.po.SubjectMappingPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表数据库访问层
 *
 */
public interface SubjectMappingDao {

    List<LabelCountPO> getLabelSubjectCount(@Param("categoryId") Long categoryId,
                                            @Param("subjectTypeList") List<Integer> subjectTypeList);

    List<SubjectMappingPO> getLabelIdsBySubjectId(Long subjectId);
}

