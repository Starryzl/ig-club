package com.tencent.practice.server.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private List<Integer> subjectTypeList;

    private Long parentId;

    private Integer categoryType;
}
