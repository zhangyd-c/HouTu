<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${beansPackage};

import ${basePackage}.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

<#include "/src/main/resources/template/annotation.include"/>
@Getter
@Setter
public class ${table.className} extends BaseEntity {
	<#list table.columns as column>
	<#if column.columnNameFirstLower != 'id' && column.columnNameFirstLower != 'insertTime' && column.columnNameFirstLower != 'updateTime'>
	private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
	</#if>
	</#list>
}
