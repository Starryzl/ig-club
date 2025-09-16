package com.tencent.practice.server.dao;

import com.tencent.practice.server.entity.po.SubjectJudgePO;

public interface SubjectJudgeDao {

    SubjectJudgePO selectBySubjectId(Long repeatSubjectId);
}
