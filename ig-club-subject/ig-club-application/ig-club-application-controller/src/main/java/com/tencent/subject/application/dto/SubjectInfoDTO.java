package com.tencent.subject.application.dto;

import com.tencent.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目dto
 *
 * @author makejava
 * @since 2024-04-21 08:58:58
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 分类id
     */
    private List<Integer> categoryIds;

    /**
     * 标签id
     */
    private List<Integer> labelIds;

    /**
     * 标签name
     */
    private List<String> labelName;

    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;

    private Long categoryId;

    private Long labelId;

    private String keyWord;
    /**
     * 创建人昵称
     */
    private String createUser;
    /**
     * 创建人头像
     */
    private String createUserAvatar;
    /**
     * 题目数量
     */
    private Integer subjectCount;
}

