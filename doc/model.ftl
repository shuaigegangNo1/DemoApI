import { BaseModule } from './BaseModule';

export class ${class?cap_first} extends BaseModule {
	  <#list modelList as model>
	  	<#if model.code!="id" && model.code!="isDelete" && model.code!="isDelete" && model.code!="creator" && model.code!="updater" && model.code!="createTime" && model.code!="updateTime" >   
	  		${model.code}?: <#if model.type?contains("int")>number<#elseif model.type?contains("char")>string<#elseif model.type?contains("time")>Date</#if>;
	  	</#if>
	  </#list>
}