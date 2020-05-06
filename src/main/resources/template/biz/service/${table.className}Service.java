<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.biz.service;


import ${basePackage}.framework.object.AbstractService;
import ${beansPackage}.${table.className};
import ${basePackage}.biz.form.${table.className}Form;
import com.github.pagehelper.PageInfo;

<#include "/src/main/resources/template/annotation.include"/>
public interface ${table.className}Service extends AbstractService<${table.className}, ${primaryKeyType}> {

    PageInfo<${table.className}> findPageBreakByCondition(${table.className}Form param);
}
