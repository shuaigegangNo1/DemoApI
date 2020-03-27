<nz-card [nzBordered]="false">
  <form nz-form [nzLayout]="'inline'" (ngSubmit)="getData()" class="search__form">
    <div nz-row [nzGutter]="{ xs: 8, sm: 8, md: 8, lg: 24, xl: 48, xxl: 48 }">
      <div nz-col nzMd="8" nzSm="24">
        <nz-form-item>
          <nz-form-label nzFor="name">查询参数 1</nz-form-label>
          <nz-form-control>
            <input nz-input [(ngModel)]="q.param1" name="param1" placeholder="请输入" id="param1" />
          </nz-form-control>
        </nz-form-item>
      </div>
      <div nz-col nzMd="8" nzSm="24">
        <nz-form-item>
          <nz-form-label nzFor="text">查询参数 2</nz-form-label>
          <nz-form-control>
            <input nz-input [(ngModel)]="q.param2" name="param2" placeholder="请输入" id="param2" />
          </nz-form-control>
        </nz-form-item>
      </div>
      <div nz-col [nzSpan]="expandForm ? 24 : 8" [class.text-right]="expandForm">
        <button nz-button type="submit" [nzType]="'primary'" [nzLoading]="loading">查询</button>
        <button nz-button type="reset" (click)="reset()" class="mx-sm">重置</button>
      </div>
    </div>
  </form>
  <button nz-button (click)="create()" [nzType]="'primary'">
    <i nz-icon nzType="plus"></i>
    <span>新建</span>
  </button>

  <st #st [columns]="columns" [data]="data" [loading]="loading" [total]="total" (change)="stChange($event)">
  </st>
</nz-card>
<ng-template #modalContent>
      <nz-form-item>
  	  <#list modelList as model>
	  	<#if model.code!="id" && model.code!="isDelete" && model.code!="isDelete" && model.code!="creator" && model.code!="updater" && model.code!="createTime" && model.code!="updateTime" >   
  		<#if model.type?contains("int")>   
	  	        <nz-form-label nzFor="${model.code}">${model.name}</nz-form-label>
		        <nz-form-control>
		          <input nz-input [(ngModel)]="${class}.${model.code}" name="${model.code}" placeholder="请输入" id="${model.code}" type="number"/>
		        </nz-form-control>
	    </#if>
		  <#if model.type?contains("char")>   
	  	        <nz-form-label nzFor="${model.code}">${model.name}</nz-form-label>
		        <nz-form-control>
		          <input nz-input [(ngModel)]="${class}.${model.code}" name="${model.code}" placeholder="请输入" id="${model.code}" />
		        </nz-form-control>
		    </#if>
		  <#if model.type?contains("time")>
  	        	<nz-form-label nzFor="${model.code}">${model.name}</nz-form-label>
		        <nz-form-control>
  			      <nz-date-picker formControlName="${model.code}" nzShowTime nzFormat="YYYY-MM-DD HH:mm:ss" nzDisabled=false
		       		 [(ngModel)]="${class}.${model.code}">
	      			</nz-date-picker>
		        </nz-form-control>   
		    </#if>
	  	</#if>
	  </#list>
      </nz-form-item>
</ng-template>