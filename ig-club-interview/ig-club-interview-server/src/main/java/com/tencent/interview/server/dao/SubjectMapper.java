package com.tencent.interview.server.dao;

import com.tencent.interview.server.entity.po.SubjectCategory;
import com.tencent.interview.server.entity.po.SubjectInfo;
import com.tencent.interview.server.entity.po.SubjectLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {

    List<SubjectLabel> listAllLabel();

    List<SubjectCategory> listAllCategory();

    List<SubjectInfo> listSubjectByLabelIds(@Param("ids") List<Long> ids);

}
