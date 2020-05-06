package me.zhyd.houtu.entity;

import lombok.Data;
import me.zhyd.houtu.util.DbUtil;
import me.zhyd.houtu.util.StringUtils;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 18:16
 * @since 1.0.0
 */
@Data
public class Table {
    /**
     * 数据库名称
     */
    private String db;
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 创建日期
     */
    private String createTime;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 编码集
     */
    private String coding;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    private List<Column> columns;

    private String className;

    /**
     * 转换类名
     *
     * @return ClassName
     */
    public String getClassName() {
        if (StringUtils.isEmpty(this.className)) {
            this.className = DbUtil.makeAllWordFirstLetterUpperCase(this.tableName);
        }
        return this.className;
    }

    /**
     * 设置类前缀
     *
     * @return Table
     */
    public Table setClassNamePrefix(String prefix) {
        if (!StringUtils.isEmpty(prefix)) {
            this.className = prefix + DbUtil.makeAllWordFirstLetterUpperCase(this.tableName);
        }
        return this;
    }

    /**
     * 清除类前缀
     *
     * @return Table
     */
    public Table clearClassPrefix(String clearClassPrefix) {
        if (!StringUtils.isEmpty(clearClassPrefix)) {
            String className = this.getClassName();
            if (className.length() < clearClassPrefix.length()) {
                return this;
            }
            String prefix = className.substring(0, clearClassPrefix.length());
            if (prefix.equalsIgnoreCase(clearClassPrefix)) {
                this.className = DbUtil.capitalize(className.substring(clearClassPrefix.length()));
            }
        }
        return this;
    }

    /**
     * 类名首字母大写
     *
     * @return ClassName
     */
    public String getClassNameFirstUpper() {
        return DbUtil.capitalize(this.getClassName());
    }

    /**
     * 类名首字母小写
     *
     * @return ClassName
     */
    public String getClassNameFirstLower() {
        return DbUtil.unCapitalize(this.getClassName());
    }
}
