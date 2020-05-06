package me.zhyd.houtu;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.houtu.config.Config;
import me.zhyd.houtu.core.GeneratorHelper;
import me.zhyd.houtu.entity.Table;
import me.zhyd.houtu.entity.Template;
import me.zhyd.houtu.util.FreemarkerUtil;
import me.zhyd.houtu.util.ListUtil;
import me.zhyd.houtu.util.TemplateUtil;
import me.zhyd.houtu.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 21:49
 * @since 1.0.0
 */
@Slf4j
public class Generator {

    // 读取所有模板
    List<Template> templates = null;
    boolean generateAll = false;
    private GeneratorHelper helper;

    public Generator(Config config) {
        this.helper = new GeneratorHelper(config);
        this.templates = TemplateUtil.listTemplates(config.getTemplatePath());
    }

    private void printBeginProcess(String tableName) {
        log.info("---------------------------------------------------------------");
        log.info("- BEGIN generate by " + tableName);
        log.info("---------------------------------------------------------------");
    }

    public void printAllTableInfo() {
        log.info("----All Table Names Begin----");
        List<Table> tableList = helper.listAllTable();
        for (Table table : tableList) {
            log.info("  {} - \"{}\" - {}", table.getEngine(), table.getTableName(), table.getCoding());
        }
        log.info("----All Table Names END----");
    }

    public void deleteOutRootDir() {
        String outRootDir = this.helper.getConfig().getOutRootDir();
        if (StringUtils.isEmpty(outRootDir)) {
            throw new IllegalStateException("'outRootDir' property must be not null.");
        }
        log.info("[delete dir]    " + outRootDir);
        FileUtil.del(outRootDir);
    }

    public void generate(String... tableNames) {

        if (null == tableNames || tableNames.length == 0) {
            this.generateAll();
            return;
        }

        // 输出文件
        for (String tableName : tableNames) {
            this.printBeginProcess(tableName);

            Table table = helper.getTableInfo(tableName);

            this.generate(table);
        }
        if (!this.generateAll) {
            this.helper.destroy();
        }
    }

    private void generate(Table... tables) {

        if (null == tables || tables.length == 0) {
            this.generateAll();
            return;
        }
        Config config = this.helper.getConfig();

        // 输出文件
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(config), Map.class);
        for (Table table : tables) {
            this.printBeginProcess(table.getTableName());
            params.put("table", table);

            List<Template> itemTemplate = ListUtil.deepClone(templates, Template.class);
            for (Template template : itemTemplate) {

                String filePath = FreemarkerUtil.template2String(template.getFilePath(), params, false);

                template.setFilePath(filePath);
                template.setFileContent(FreemarkerUtil.template2String(template.getFileContent(), params, false));
            }

            this.generateFiles(itemTemplate);
        }
        if (!this.generateAll) {
            this.helper.destroy();
        }
    }

    public void generateAll() {
        this.generateAll = true;
        List<Table> tables = this.helper.listAllTable();
        for (Table table : tables) {
            this.generate(table);
        }
        this.helper.destroy();
        generateAll = false;
    }

    private void generateFiles(List<Template> files) {
        Config config = this.helper.getConfig();
        for (Template file : files) {
            String finalOutPath = config.getOutRootDir() + file.getFilePath();
            log.info(finalOutPath);
            FileUtil.writeString(file.getFileContent(), finalOutPath, StandardCharsets.UTF_8);
        }
    }
}
