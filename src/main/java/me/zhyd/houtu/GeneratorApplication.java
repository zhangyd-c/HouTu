package me.zhyd.houtu;


import me.zhyd.houtu.config.Config;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

public class GeneratorApplication {

    @Test
    public void main() {

        Generator g = new Generator(Config.getInstance()
                .setDriver("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/houtu?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false")
                .setUsername("root")
                .setPassword("root")
                .setPrimaryKeyType("Integer")
                .setClassPrefix("HouTu")
                .setClearClassPrefix("HouTu")
                .setOutRootDir(System.getProperty("user.dir") + File.separator + "generator-output"));

        //删除生成器的输出目录
        g.deleteOutRootDir();

        // 打印所有表
        g.printAllTableInfo();

        // 生成单个表的Java文件
        g.generate("sys_user");
        // 生成多个表的Java文件
//        g.generate("links", "user");
        // 生成所有表的Java文件
//        g.generate();
        // 生成所有表的Java文件
//        g.generateAll();
    }

    @Test
    public void printProperty() {
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        Properties properties = System.getProperties();
        for (String propertyName : properties.stringPropertyNames()) {
            System.out.println(propertyName + " = " + System.getProperty(propertyName));
        }
    }

}
