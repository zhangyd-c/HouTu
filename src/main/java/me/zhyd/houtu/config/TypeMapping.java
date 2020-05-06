package me.zhyd.houtu.config;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL type和java type映射关系
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 18:25
 * @since 1.0.0
 */
public class TypeMapping {

    private static final String STRING_TYPE = "java.lang.String";
    private static Map<Integer, String> mapping = new HashMap<>();

    static {
        mapping.put(Types.TINYINT, "java.lang.Boolean");
        mapping.put(Types.SMALLINT, "java.lang.Integer");
        mapping.put(Types.INTEGER, "java.lang.Integer");
        mapping.put(Types.BIGINT, "java.lang.Long");
        mapping.put(Types.REAL, "java.lang.Float");
        mapping.put(Types.FLOAT, "java.lang.Double");
        mapping.put(Types.DOUBLE, "java.lang.Double");
        mapping.put(Types.DECIMAL, "java.math.BigDecimal");
        mapping.put(Types.NUMERIC, "java.math.BigDecimal");
        mapping.put(Types.BIT, "java.lang.Boolean");
        mapping.put(Types.BOOLEAN, "java.lang.Boolean");
        mapping.put(Types.CHAR, "java.lang.String");
        mapping.put(Types.VARCHAR, "java.lang.String");
        mapping.put(Types.LONGVARCHAR, "java.lang.String");
        mapping.put(Types.BINARY, "byte[]");
        mapping.put(Types.VARBINARY, "byte[]");
        mapping.put(Types.LONGVARBINARY, "byte[]");
        mapping.put(Types.DATE, "java.sql.Date");
        mapping.put(Types.TIME, "java.sql.Time");
        mapping.put(Types.TIMESTAMP, "java.sql.Timestamp");
        mapping.put(Types.CLOB, "java.sql.Clob");
        mapping.put(Types.BLOB, "java.sql.Blob");
        mapping.put(Types.ARRAY, "java.sql.Array");
        mapping.put(Types.REF, "java.sql.Ref");
        mapping.put(Types.STRUCT, "java.lang.Object");
        mapping.put(Types.JAVA_OBJECT, "java.lang.Object");

    }

    public static String getJavaType(String sqlType) {
        if (null == sqlType || sqlType.isEmpty()) {
            return STRING_TYPE;
        }
        JdbcType jdbcType = JdbcType.getJdbcTypeBySqlType(sqlType);
        if (mapping.containsKey(jdbcType.getCode())) {
            return mapping.get(jdbcType.getCode());
        }
        return STRING_TYPE;
    }
}
