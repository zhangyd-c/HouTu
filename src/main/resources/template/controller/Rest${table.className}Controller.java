<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.controller;

import ${beansPackage}.${table.className};
import ${basePackage}.biz.service.${table.className}Service;
import ${basePackage}.biz.form.${table.className}Form;
import ${basePackage}.framework.object.PageResult;
import ${basePackage}.framework.rest.R;
import ${basePackage}.framework.rest.RestStatus;
import ${basePackage}.util.RenderUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<#include "/src/main/resources/template/annotation.include"/>
@RestController
@RequestMapping("/backend/${table.classNameFirstLower}")
public class Rest${table.className}Controller {
    @Autowired
    private ${table.className}Service ${table.classNameFirstLower}Service;

    @PostMapping("/list")
    public PageResult getAll(${table.className}Form param) {
        PageInfo<${table.className}> pageInfo = ${table.classNameFirstLower}Service.findPageBreakByCondition(param);
        return RenderUtil.tablePage(pageInfo);
    }

    @PostMapping(value = "/add")
    public R add(${table.className} entity) {
        ${table.classNameFirstLower}Service.insert(entity);
        return R.success();
    }

    @PostMapping(value = "/remove")
    public R remove(${primaryKeyType}[] ids) {
        ${table.classNameFirstLower}Service.removeByPkList(ids);
        return R.success(RestStatus.SUCCESS);
    }

    @PostMapping("/get/{id}")
    public R get(@PathVariable ${primaryKeyType} id) {
        return R.success(this.${table.classNameFirstLower}Service.getByPrimaryKey(id));
    }

    @PostMapping("/edit")
    public R edit(${table.className} entity) {
        ${table.classNameFirstLower}Service.updateSelective(entity);
        return R.success(RestStatus.SUCCESS);
    }

}
