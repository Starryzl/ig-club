package com.tencent.auth.infra.basic.mapper;

import com.tencent.auth.infra.basic.entity.AuthUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户角色表(AuthUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-22 13:46:13
 */
public interface AuthUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);

    /**
     * 查询指定行数据
     */
    List<AuthUserRole> queryAllByLimit(AuthUserRole authUserRole);

    /**
     * 统计总行数
     *
     * @param authUserRole 查询条件
     * @return 总行数
     */
    long count(AuthUserRole authUserRole);

    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 影响行数
     */
    int insert(AuthUserRole authUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthUserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthUserRole> entities);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 影响行数
     */
    int update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

