package com.tencent.subject.infra.basic.service;

import com.tencent.subject.infra.basic.entity.SubjectRadio;

import java.util.List;


/**
 * 单选题信息表(SubjectRadio)表服务接口
 *
 * @author makejava
 * @since 2024-04-21 09:00:42
 */
public interface SubjectRadioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 批量插入
     * @param subjectRadioList
     * @return
     */
    void batchInsert(List<SubjectRadio> subjectRadioList);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);
}
