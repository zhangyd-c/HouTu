<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.biz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Getter
@Setter
public class ${table.className}Dto {

}

