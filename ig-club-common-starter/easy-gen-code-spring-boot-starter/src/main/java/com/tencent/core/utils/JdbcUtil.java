package com.tencent.core.utils;

import com.tencent.core.core.MysqlDataHandler;
import com.tencent.core.entity.JdbcConfig;
import com.tencent.core.entity.TableInfo;

import java.util.List;

/**
 * jdbc工具 用于查询表字段信息 及 表结构信息
 *
 * @author loser
 * @date 2023/9/4
 */
public class JdbcUtil {

    private JdbcUtil() {
    }

    public static List<TableInfo> queryTableInfo(JdbcConfig config, String table) {

        return new MysqlDataHandler(table, config.getUrl(), config.getUsername(),
                config.getPassword(), config.getDbName(), config.getDriver())
                .searchByDb();

    }

    public static String getComment(JdbcConfig config, String table) {

        return new MysqlDataHandler(table, config.getUrl(), config.getUsername(),
                config.getPassword(), config.getDbName(), config.getDriver())
                .getTableComment();

    }

}
