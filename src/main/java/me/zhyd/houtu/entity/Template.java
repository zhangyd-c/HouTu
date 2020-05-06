package me.zhyd.houtu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/4/26 22:42
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Template implements Cloneable {

    private String filePath;
    private String fileContent;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
