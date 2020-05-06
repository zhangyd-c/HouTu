package me.zhyd.houtu.config;

import lombok.Data;
import me.zhyd.houtu.consts.GeneratorConst;
import me.zhyd.houtu.exception.GeneratorException;
import me.zhyd.houtu.util.DbUtil;

/**
 * 代码生成器配置文件:
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 18:21
 * @since 1.0.0
 */
@Data
public class Config {

    /**
     * 数据库驱动
     */
    private String driver = "com.mysql.jdbc.Driver";
    /**
     * 数据库地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 选择的数据库
     */
    private String db;
    /**
     * 生成的类前缀，先去除{@code clearClassPrefix}再添加{@code classPrefix}
     */
    private String classPrefix;
    /**
     * 需要去除的类前缀，先去除{@code clearClassPrefix}再添加{@code classPrefix}
     */
    private String clearClassPrefix;
    /**
     * 主键类型，当批量生成文件时，如果多个表的主键类型不一致，则最终生成的代码可能存在问题
     */
    private String primaryKeyType = "Integer";
    /**
     * 根包名
     */
    private String basePackage = "me.zhyd.light.core";
    /**
     * beans包名
     */
    private String beansPackage = basePackage + ".db.persistence.beans";
    /**
     * mapper接口包名
     */
    private String mapperPackage = basePackage + ".db.persistence.mapper";

    /**
     * 文件输出路径
     */
    private String outRootDir;

    /**
     * 模板地址
     */
    private String templatePath = "template/";

    public static Config getInstance() {
        return new Config();
    }

    public String getDb() {
        if (this.db == null || this.db.isEmpty()) {
            return DbUtil.getDbName(this.url);
        }
        return this.db;
    }

    public Config setDb(String db) {
        this.db = db;
        return this;
    }

    public String getUrl() {
        if (this.url == null || this.url.isEmpty()) {
            throw new GeneratorException("数据库链接不可为空");
        }
        if (url.contains("?")) {
            return url;
        }
        return url + GeneratorConst.DEF_DB_URL_PARAMS;
    }

    public Config setUrl(String url) {
        this.url = url;
        return this;
    }

    public Config setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public Config setUsername(String username) {
        this.username = username;
        return this;
    }

    public Config setPassword(String password) {
        this.password = password;
        return this;
    }

    public Config setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
        return this;
    }

    public Config setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    public Config setBeansPackage(String beansPackage) {
        this.beansPackage = beansPackage;
        return this;
    }

    public Config setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
        return this;
    }

    public Config setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
        return this;
    }

    public Config setOutRootDir(String outRootDir) {
        this.outRootDir = outRootDir;
        return this;
    }

    public Config setClearClassPrefix(String clearClassPrefix) {
        this.clearClassPrefix = clearClassPrefix;
        return this;
    }

    public Config setPrimaryKeyType(String primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
        return this;
    }
}
