package me.zhyd.houtu.entity;

import lombok.Data;
import me.zhyd.houtu.config.JdbcType;
import me.zhyd.houtu.config.TypeMapping;
import me.zhyd.houtu.util.DbUtil;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 18:17
 * @since 1.0.0
 */
@Data
public class Column {

    private String tableName;

    /**
     * 数据库字段名称
     */
    private String columnName;

    /**
     * 数据库字段类型
     */
    private String columnType;

    /**
     * 数据库字段长度
     */
    private Integer length;

    /**
     * 数据库字段小数位长度
     */
    private Integer scale;

    /**
     * 数据库字段键类型
     */
    private String keyType;

    /**
     * 字段额外的参数
     */
    private String extra;

    /**
     * 数据库字段描述
     */
    private String remark;

    /**
     * 必填
     */
    private Boolean notNull;

    /**
     * 第一个字母小写的columName,等价于: StringHelper.uncapitalize(getColumnName()),示例值: birthDate
     **/
    public String getColumnNameFirstUpper() {
        String columnName = DbUtil.makeAllWordFirstLetterUpperCase(this.columnName);
        return DbUtil.capitalize(columnName);
    }

    /**
     * 第一个字母小写的columName,等价于: StringHelper.uncapitalize(getColumnName()),示例值: birthDate
     **/
    public String getColumnNameFirstLower() {
        String columnName = DbUtil.makeAllWordFirstLetterUpperCase(this.columnName);
        return DbUtil.unCapitalize(columnName);
    }

    public String getJavaType() {
        String columnName = DbUtil.makeAllWordFirstLetterUpperCase(this.columnName);
        return TypeMapping.getJavaType(columnName);
    }

    /**
     * 得到 jdbcSqlType类型名称，示例值:VARCHAR,DECIMAL, 现Ibatis3使用该属性
     */
    public String getJdbcType() {
        JdbcType jdbcType = JdbcType.getJdbcTypeBySqlType(columnType);
        return jdbcType.name();
    }

    /**
     * 得到尽可能简短的javaType的名称，如果是java.lang.String,将返回String, 如com.company.model.UserInfo,将返回 com.company.model.UserInfo
     */
    public String getPossibleShortJavaType() {
        if (getJavaType().startsWith("java.lang.") || getJavaType().startsWith("java.util.")) {
            return DbUtil.getJavaClassSimpleName(getJavaType());
        } else {
            return getJavaType();
        }
    }

}
