package com.tencent.practice.server.service;

import com.tencent.practice.api.req.GetPracticeSubjectsReq;
import com.tencent.practice.api.vo.PracticeSetVO;
import com.tencent.practice.api.vo.PracticeSubjectListVO;
import com.tencent.practice.api.vo.PracticeSubjectVO;
import com.tencent.practice.api.vo.SpecialPracticeVO;
import com.tencent.practice.server.entity.dto.PracticeSubjectDTO;

import java.util.List;

public interface PracticeSetService {

    /**
     * 获取专项练习内容
     */
    List<SpecialPracticeVO> getSpecialPracticeContent();

    /**
     * 开始练习
     */
    PracticeSetVO addPractice(PracticeSubjectDTO dto);

    /**
     * 获取练习题
     */
    PracticeSubjectListVO getSubjects(GetPracticeSubjectsReq req);

    /**
     * 获取题目
     */
    PracticeSubjectVO getPracticeSubject(PracticeSubjectDTO dto);

}
