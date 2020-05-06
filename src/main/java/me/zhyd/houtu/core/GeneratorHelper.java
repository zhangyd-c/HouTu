package me.zhyd.houtu.core;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.houtu.config.Config;
import me.zhyd.houtu.entity.Column;
import me.zhyd.houtu.entity.Table;
import me.zhyd.houtu.exception.GeneratorException;
import me.zhyd.houtu.util.CloseableUtil;
import me.zhyd.houtu.util.ListUtil;
import me.zhyd.houtu.util.StringUtils;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据访问层，提供基本的数据库信息查询
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 18:20
 * @since 1.0.0
 */
@Slf4j
public class GeneratorHelper {
    private static boolean connected = false;
    Connection conn = null;
    Statement statement = null;
    Config config = null;
    List<String> databases = null;
    List<Table> tables = null;

    public GeneratorHelper(Config config) {
        this.init(config);
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

    /**
     * 初始化mysql链接信息
     *
     * @param config 配置类
     */
    private void init(Config config) {
        this.init(config.getDriver(), config.getUrl(), config.getUsername(), config.getPassword());
    }

    /**
     * 初始化mysql链接信息
     *
     * @param driver   驱动类
     * @param url      数据库url
     * @param username 用户名
     * @param password 密码
     */
    private void init(String driver, String url, String username, String password) {
        if (connected) {
            return;
        }
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            connected = true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new GeneratorException(String.format("数据库链接失败！请检查数据库参数是否配置正确 \ndriver = %s \nurl = %s \nusername = %s \npassword = %s", driver, url, username, password), e);
        }
    }

    /**
     * 销毁资源
     */
    public void destroy() {
        if(connected) {
            log.info("程序执行完毕，释放资源...");
            connected = false;
            this.databases = null;
            this.tables = null;
            CloseableUtil.close(conn, statement);
        }
    }

    /**
     * 执行SQL
     *
     * @param sql sql信息
     * @return ResultSet
     * @throws SQLException SQLException
     */
    private ResultSet exe(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    /**
     * 获取数据库列表
     *
     * @return 数据库列表
     */
    public List<String> listDatabase() {
        if (!ListUtil.isEmpty(this.databases)) {
            return this.databases;
        }
        List<String> list = Lists.newArrayList();
        ResultSet rs = null;
        try {
            String sql = "show databases";
            rs = exe(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new GeneratorException("无法获取database列表", e);
        } finally {
            CloseableUtil.close(rs);
        }
        this.databases = list;
        return list;
    }

    /**
     * 获取所有的table信息
     *
     * @return table列表
     */
    public List<Table> listAllTable() {
        if (!ListUtil.isEmpty(this.tables)) {
            return this.tables;
        }
        String schema = this.config.getDb();
        this.checkDatabase(schema);

        this.tables = this.getTables(null, schema);
        return this.tables;
    }

    /**
     * 获取表列表，如果传入 单个表名， 则只获取单个表的信息。否则获取所有的表信息
     *
     * @param tableName 表名
     * @param schema    数据库名
     * @return 如果传入 单个表名， 则只获取单个表的信息。否则获取所有的表信息
     */
    private List<Table> getTables(String tableName, String schema) {
        String sql = "select distinct table_schema, table_name, table_type, engine, create_time, table_comment, table_collation from information_schema.tables where table_schema = '" + schema + "'";
        if (!StringUtils.isEmpty(tableName)) {
            sql += " and table_name = '" + tableName + "'";
        }
        List<Table> list = Lists.newArrayList();
        Table table = null;
        ResultSet rs = null;
        try {
            rs = exe(sql);
            while (rs.next()) {
                String realTableName = rs.getString("table_name");
                String tableSchema = rs.getString("table_schema");
                String createTime = rs.getString("create_time");
                String remarks = rs.getString("table_comment");
                String engine = rs.getString("engine");
                String coding = rs.getString("table_collation");
                String type = rs.getString("table_type");

                table = new Table();
                table.setDb(tableSchema);
                table.setTableName(realTableName);
                table.setType(type);
                table.setCreateTime(createTime);
                table.setEngine(engine);
                table.setCoding(coding);
                table.setRemark(remarks);
                list.add(table);
            }
        } catch (SQLException e) {
            throw new GeneratorException(e.getMessage(), e);
        } finally {
            CloseableUtil.close(rs);
        }

        if (!ListUtil.isEmpty(list)) {
            list.forEach((t) -> {
                t.clearClassPrefix(this.config.getClearClassPrefix())
                        .setClassNamePrefix(this.config.getClassPrefix())
                        .setColumns(this.getTableColumnInfos(t.getTableName(), schema));
            });
        }
        return list;
    }

    /**
     * 获取表信息
     *
     * @param tableName 表名
     */
    public Table getTableInfo(String tableName) {
        return this.getTableInfo(tableName, this.config.getDb(), true);
    }

    /**
     * 获取表信息
     *
     * @param tableName  表名
     * @param schema     数据库名
     * @param checkTable 是否校验表的合法性
     */
    public Table getTableInfo(String tableName, String schema, boolean checkTable) {
        if (checkTable) {
            this.checkTable(tableName, schema);
        }

        List<Table> list = this.getTables(tableName, schema);
        return list.get(0);
    }

    /**
     * 获取表字段信息
     *
     * @param table  表名
     * @param schema 数据库
     * @return 列信息
     */
    private List<Column> getTableColumnInfos(String table, String schema) {
        List<Column> cols = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct table_schema, table_name, column_name, column_default, is_nullable, data_type, character_maximum_length, numeric_precision, numeric_scale, character_set_name, collation_name, column_type, column_key, extra, column_comment \n" +
                    "from information_schema.columns \n" +
                    "where table_name = '" + table + "' and table_schema = '" + schema + "'";
            rs = exe(sql);
            cols = Lists.newLinkedList();
            while (rs.next()) {
                Column column = new Column();
                column.setTableName(rs.getString("table_name"));
                column.setColumnName(rs.getString("column_name"));
                column.setColumnType(rs.getString("data_type"));
                column.setRemark(rs.getString("column_comment"));
                column.setNotNull("NO".equalsIgnoreCase(rs.getString("is_nullable")));
                if (Arrays.asList("decimal", "double", "float").contains(column.getColumnType())) {
                    column.setLength(rs.getInt("numeric_precision"));
                    column.setScale(rs.getInt("numeric_scale"));
                } else {
                    column.setLength(rs.getInt("character_maximum_length"));
                }
                column.setKeyType(rs.getString("column_key"));
                column.setExtra(rs.getString("extra"));
                cols.add(column);
            }
        } catch (SQLException e) {
            throw new GeneratorException("无法获取[" + table + "]表的列信息", e);
        }
        return cols;
    }

    /**
     * 校验数据库有效性
     */
    private void checkDatabase(String schema) {
        List<String> databases = this.listDatabase();
        if (!databases.contains(schema)) {
            throw new GeneratorException("数据库 [" + schema + "] 不存在");
        }
    }

    /**
     * 校验数据库有效性
     *
     * @param table  表名
     * @param schema 数据库名
     */
    private void checkTable(String table, String schema) {
        List<Table> tables = this.listAllTable();
        List<String> tableNames = tables.stream().map(Table::getTableName).collect(Collectors.toList());
        if (!tableNames.contains(table)) {
            throw new GeneratorException("数据库 [" + schema + "] 中不存在 [" + table + "] 表");
        }
    }
}
