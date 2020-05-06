package me.zhyd.houtu.util;

import cn.hutool.core.io.IoUtil;
import com.google.common.collect.Lists;
import me.zhyd.houtu.entity.Template;
import me.zhyd.houtu.exception.GeneratorException;
import me.zhyd.houtu.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 22:21
 * @since 1.0.0
 */
public class TemplateUtil extends cn.hutool.core.io.FileUtil {

    private static String getFileContentByResourcePath(String resourcePath) {
        try {
            InputStream inputStream = TemplateUtil.class.getResourceAsStream(resourcePath);
            if (null == inputStream) {
                throw new GeneratorException("请检查`src/main/resources`下是否存在: " + resourcePath);
            }
            String content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(content)) {
                throw new GeneratorException("模板内容为空：" + resourcePath);
            }
            return content;
        } catch (Exception e) {
            throw new GeneratorException("无法获取模板内容：" + resourcePath);
        }
    }

    private static String getFileContentByFilePath(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            String content = IoUtil.read(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.isEmpty(content)) {
                throw new GeneratorException("模板内容为空：" + filePath);
            }
            return content;
        } catch (Exception e) {
            throw new GeneratorException("无法获取模板内容：" + filePath);
        }
    }

    private static List<File> getFilesFromResource(String resourcePath) {
        List<File> files = loopFiles(resourcePath);
        return files.stream().filter((f) -> !f.getPath().endsWith(".include")).collect(Collectors.toList());
    }

    public static List<Template> listTemplates(String templatePath) {

        URL classPath = Thread.currentThread().getContextClassLoader().getResource(templatePath);
        if (null == classPath) {
            throw new GeneratorException("[" + templatePath + "] 下未获取到模板信息");
        }
        String templateBasePath = file(classPath.getFile()).getAbsolutePath();
        List<Template> templates = Lists.newArrayList();
        List<File> templateFiles = TemplateUtil.getFilesFromResource(templateBasePath);

        for (File template : templateFiles) {
            String absolutePath = template.getAbsolutePath();
            templates.add(Template.builder()
                    .filePath(absolutePath.replace(templateBasePath, ""))
                    .fileContent(TemplateUtil.getFileContentByFilePath(template.getPath()))
                    .build());
        }
        return templates;
    }


    /**
     * 删除目录，返回删除的文件数
     *
     * @param rootPath 待删除的目录
     */
    public static void deleteDir(String rootPath) {
        File dir = new File(rootPath);
        if (!dir.exists()) {
            return;
        }
        if (!dir.isDirectory()) {
            String message = dir + " is not a directory";
            throw new IllegalArgumentException(message);
        }
        File[] items = dir.listFiles();
        if (items != null && items.length > 0) {
            for (File item : items) {
                forceDelete(item);
            }
        }
    }

    /**
     * 强制删除文件
     *
     * @param file 待删除的文件
     */
    public static void forceDelete(File file) {
        if (file.isDirectory()) {
            deleteDir(file.getAbsolutePath());
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new GeneratorException("File does not exist: " + file);
                }
                String message = "Unable to delete file: " + file;
                throw new GeneratorException(message);
            }
        }
    }
}
