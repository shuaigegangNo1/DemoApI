<page-header [title]="${class}.id?'修改${title}':'创建${title}'">
</page-header>
<nz-card [nzBordered]="false">
  <form nz-form [formGroup]="form" se-container="1" labelWidth="200">
  <#list modelList as model>
    	  <#if model.type?contains("int") && model.code!="id" && model.code!="isDelete" >   
		    <se label="${model.name}" error="请输入${model.name}" <#if model.isNull=="NO">required</#if> >
		      <input nz-input formControlName="${model.code}" placeholder="请你的输入${model.name}" [(ngModel)]="${class}.${model.code}"
		        style="width: calc(100% - 400px);" type="number" />
		    </se>
	    </#if>
	  <#if model.type?contains("char")>   
		    <se label="${model.name}" error="请输入${model.name}" <#if model.isNull=="NO">required</#if>>
		      <input nz-input formControlName="${model.code}" placeholder="请你的输入${model.name}" [(ngModel)]="${class}.${model.code}"
		        style="width: calc(100% - 400px);" />
		    </se>
	    </#if>
	  <#if model.type?contains("time")>   
		    <se label="${model.name}" error="请输入${model.name}" <#if model.isNull=="NO">required</#if>>
		      <nz-date-picker formControlName="${model.code}" nzShowTime nzFormat="YYYY-MM-DD HH:mm:ss" nzDisabled=false
		        [(ngModel)]="${class}.${model.code}">
		      </nz-date-picker>
		    </se>
	    </#if>
    </#list>
    <se>
      <button *ngIf="${class}.id" nz-button nzType="primary" (click)="update()" [disabled]="form.invalid">提交</button>
      <button *ngIf="!${class}.id" nz-button class="ml-sm">保存</button>
      <button nz-button (click)="goback()">返回</button>
    </se>
  </form>
</nz-card>