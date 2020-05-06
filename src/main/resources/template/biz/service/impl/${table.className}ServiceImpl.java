<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.biz.service.impl;

import ${basePackage}.biz.service.${table.className}Service;
import ${basePackage}.biz.form.${table.className}Form;
import ${beansPackage}.${table.className};
import ${mapperPackage}.${table.className}Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

<#include "/src/main/resources/template/annotation.include"/>
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ${table.className}ServiceImpl implements ${table.className}Service {

    private final ${table.className}Mapper ${classNameLower}Mapper;

    @Override
    public PageInfo<${table.className}> findPageBreakByCondition(${table.className}Form param) {
        PageHelper.startPage(param.getPageNumber(), param.getPageSize());
        Example example = new Example(${table.className}.class);

        example.orderBy("createTime").desc();

        List<${table.className}> list = ${classNameLower}Mapper.selectByExample(example);
        return new PageInfo<${table.className}>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.className} insert(${table.className} entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        ${classNameLower}Mapper.insertSelective(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(${primaryKeyType} id) {
        Assert.notNull(id, "Invalid parameter");
        return ${classNameLower}Mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPkList(Integer[] primaryKeys) {
        Assert.noNullElements(primaryKeys, "ID 不可为空");
        Example example = new Example(${table.className}.class);
        example.createCriteria().andIn("id", Arrays.asList(primaryKeys));
        int removedCount = ${classNameLower}Mapper.deleteByExample(example);
        return removedCount > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(${table.className} entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setUpdateTime(new Date());
        return ${classNameLower}Mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public ${table.className} getByPrimaryKey(${primaryKeyType} id) {
        Assert.notNull(id, "Invalid parameter");
        return ${classNameLower}Mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<${table.className}> listAll() {
        return ${classNameLower}Mapper.selectAll();
    }
}
