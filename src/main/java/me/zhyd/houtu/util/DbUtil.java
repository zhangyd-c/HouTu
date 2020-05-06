package me.zhyd.houtu.util;

import me.zhyd.houtu.exception.GeneratorException;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 20:27
 * @since 1.0.0
 */
public class DbUtil {

    public static String getDbName(String url) {
        if (null == url || url.isEmpty()) {
            throw new GeneratorException("数据库链接地址不可为空！");
        }
        String[] urlSplit = url.split("\\?");
        String baseUrl = urlSplit[0];
        // 获取数据库名
        return baseUrl.substring(baseUrl.lastIndexOf("/") + 1);
    }


    public static String makeAllWordFirstLetterUpperCase(String tableName) {
        String[] strs = tableName.toLowerCase().split("_");
        StringBuilder result = new StringBuilder();
        String preStr = "";
        for (String str : strs) {
            if (preStr.length() == 1) {
                result.append(str);
            } else {
                result.append(capitalize(str));
            }
            preStr = str;
        }
        return result.toString();
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return str
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return str
     */
    public static String unCapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * 更换首字母
     *
     * @param str        字符串
     * @param capitalize 是否大写首字母
     * @return str
     */
    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    public static String getJavaClassSimpleName(String javaType) {
        if (javaType == null) {
            return null;
        }
        if (javaType.lastIndexOf(".") >= 0) {
            return javaType.substring(javaType.lastIndexOf(".") + 1);
        }
        return javaType;
    }
}
