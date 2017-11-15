/**
 * Copyright  ${createDate?string("yyyy-MM-dd HH:mm:ss")} 第七大道-技术支持部-网站开发组
 * 自主运营平台WEB 上午11:49:20
 * 版本号： v1.0
*/
package ${package};

<#list importTypes as t>  
import ${t};   	 
</#list> 

/***
 *类描述:<#if tableRemark?exists &&  tableRemark != ''> ${tableRemark}<#else> ${class }</#if>
 *
 */ 
public class ${class}{

 <#list properties as prop> 
 	/**<#if prop.remark?exists &&  prop.remark != ''> ${prop.remark} <#else> ${prop.name }</#if>*/
    private ${prop.simpleType} ${prop.name};  
 </#list>  
  
  <#list properties as prop>  
    public ${prop.simpleType} get${prop.name?cap_first}(){  
      return ${prop.name};  
    }  
    public void set${prop.name?cap_first}(${prop.simpleType} ${prop.name}){  
      this.${prop.name} = ${prop.name};  
    }  
  </#list>  
	
}