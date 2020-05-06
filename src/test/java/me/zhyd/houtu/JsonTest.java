package me.zhyd.houtu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.houtu.util.StringUtils;
import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/27 11:41
 * @since 1.0.0
 */
public class JsonTest {

    String jsonText = "{\n" +
            "\t\"classPrefix\": \"\",\n" +
            "\t\"password\": \"root\",\n" +
            "\t\"driver\": \"com.mysql.cj.jdbc.Driver\",\n" +
            "\t\"basePackage\": \"me.zhyd.houtu.core\",\n" +
            "\t\"mapperPackage\": \"me.zhyd.houtu.core.db.persistence.mapper\",\n" +
            "\t\"outRootDir\": \"D:\\\\project\\\\HouTu\\\\generator-output\",\n" +
            "\t\"db\": \"houtu\",\n" +
            "\t\"templatePath\": \"template/\",\n" +
            "\t\"url\": \"jdbc:mysql://127.0.0.1:3306/houtu?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false\",\n" +
            "\t\"table\": {\n" +
            "\t\t\"className\": \"HouTuLinks\",\n" +
            "\t\t\"classNameFirstLower\": \"houtuLinks\",\n" +
            "\t\t\"classNameFirstUpper\": \"HouTuLinks\",\n" +
            "\t\t\"coding\": \"utf8mb4_general_ci\",\n" +
            "\t\t\"columns\": [{\n" +
            "\t\t\t\"columnName\": \"id\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"id\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Id\",\n" +
            "\t\t\t\"columnType\": \"bigint\",\n" +
            "\t\t\t\"extra\": \"auto_increment\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"BIGINT\",\n" +
            "\t\t\t\"keyType\": \"PRI\",\n" +
            "\t\t\t\"length\": 0,\n" +
            "\t\t\t\"notNull\": true,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"create_time\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"createTime\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"CreateTime\",\n" +
            "\t\t\t\"columnType\": \"datetime\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"DATETIME\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 0,\n" +
            "\t\t\t\"notNull\": true,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"update_time\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"updateTime\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"UpdateTime\",\n" +
            "\t\t\t\"columnType\": \"datetime\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"DATETIME\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 0,\n" +
            "\t\t\t\"notNull\": true,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"name\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"name\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Name\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"MUL\",\n" +
            "\t\t\t\"length\": 50,\n" +
            "\t\t\t\"notNull\": true,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"logo\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"logo\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Logo\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 200,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"url\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"url\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Url\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 200,\n" +
            "\t\t\t\"notNull\": true,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"description\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"description\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Description\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 300,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"email\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"email\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Email\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 100,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"sort\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"sort\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Sort\",\n" +
            "\t\t\t\"columnType\": \"int\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 0,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"status\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"status\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Status\",\n" +
            "\t\t\t\"columnType\": \"bit\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"BIT\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 0,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"remark\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"remark\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Remark\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 500,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"columnName\": \"source\",\n" +
            "\t\t\t\"columnNameFirstLower\": \"source\",\n" +
            "\t\t\t\"columnNameFirstUpper\": \"Source\",\n" +
            "\t\t\t\"columnType\": \"varchar\",\n" +
            "\t\t\t\"extra\": \"\",\n" +
            "\t\t\t\"javaType\": \"java.lang.String\",\n" +
            "\t\t\t\"jdbcType\": \"VARCHAR\",\n" +
            "\t\t\t\"keyType\": \"\",\n" +
            "\t\t\t\"length\": 255,\n" +
            "\t\t\t\"notNull\": false,\n" +
            "\t\t\t\"possibleShortJavaType\": \"String\",\n" +
            "\t\t\t\"remark\": \"\",\n" +
            "\t\t\t\"tableName\": \"links\"\n" +
            "\t\t}],\n" +
            "\t\t\"createTime\": \"2020-04-26 13:25:37\",\n" +
            "\t\t\"db\": \"houtu\",\n" +
            "\t\t\"engine\": \"InnoDB\",\n" +
            "\t\t\"remark\": \"\",\n" +
            "\t\t\"tableName\": \"links\",\n" +
            "\t\t\"type\": \"BASE TABLE\"\n" +
            "\t},\n" +
            "\t\"beansPackage\": \"me.zhyd.houtu.core.db.persistence.beans\",\n" +
            "\t\"username\": \"root\"\n" +
            "}";

    @Test
    public void parse() {

        System.out.println("|       配置项        |        类型        |        示例        |        解释        |    ");
        System.out.println("|:---------------------------|:---------|:---------|:---------|    ");

        JSONObject json = JSONObject.parseObject(jsonText);
        parseObject(json, "", 0);
    }

    private void parseObject(JSONObject json, String parentKey, int deepIndex) {
        parentKey = StringUtils.isEmpty(parentKey) ? "" : parentKey;
        deepIndex = deepIndex + 1;
        for (String s : json.keySet()) {
            Object object = json.get(s);
            String newFullKey = (StringUtils.isEmpty(parentKey) ? "" : parentKey + ".") + s;
            String itemType = object.getClass().getTypeName();
            itemType = itemType.substring(itemType.lastIndexOf(".") + 1);
            if (object instanceof JSONObject) {
                System.out.println(String.format("| " + this.printSpace(deepIndex) + "- `%s` | `%s` | object |  |    ", newFullKey, itemType));
                parseObject((JSONObject) object, newFullKey, deepIndex);
            } else if (object instanceof JSONArray) {
                System.out.println(String.format("| " + this.printSpace(deepIndex) + "- `%s` | `%s` | array |  |    ", newFullKey, itemType));
                JSONArray array = (JSONArray) object;
                parseObject(array.getJSONObject(0), newFullKey, deepIndex);
            } else {
                System.out.println(String.format("| " + this.printSpace(deepIndex) + "- `%s` | `%s` | %s |  |    ", newFullKey, itemType, object));
            }
        }
    }

    private String printSpace(int deepIndex) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ((deepIndex - 1) * 2); i++) {
            res.append(" ");
        }
        return res.toString();
    }
}
