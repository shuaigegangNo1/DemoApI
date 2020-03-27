import { Component, OnInit, ViewChild, TemplateRef, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { STComponent, STColumn, STData, STChange } from '@delon/abc';
import { ${class?cap_first} } from 'src/app/common/model/${class?cap_first}';
import { ${class?cap_first}Service } from 'src/app/common/service/${class}Service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-${class}-list',
  templateUrl: './${class}list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ${class?cap_first}ListComponent implements OnInit {
  q: any = {
    pi: 0,
    ps: 10,
    sorter: '',
    status: null,
    statusList: [],
    param1: '',
    param2: '',
  };
  data: any[] = [];
  loading = false;
  ${class}: ${class?cap_first} = new ${class?cap_first}();
  @ViewChild('st', { static: true }) st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
  <#list modelList as model>
	<#if model.code!="id" && model.code!="isDelete" && model.code!="isDelete" && model.code!="creator" && model.code!="updater" && model.code!="createTime" && model.code!="updateTime" >
    { title: '${model.name}', index: '${model.code}' },
    </#if>
  </#list>
    {
      title: '操作',
      buttons: [
        {
          text: '详情',
          click: (item: any) => {
            this.router.navigate(['/${class}/${class}detail'], { queryParams: { id: item.id } })
          },
        },
        {
          text: '删除',
          click: (item: any) => {
            this.showDeleteConfirm(item.id);
          },
        },
      ],
    },
  ];
  selectedRows: STData[] = [];
  totalCallNo = 0;
  expandForm = false;
  constructor(
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private cdr: ChangeDetectorRef,
    private ${class}Service: ${class?cap_first}Service,
    private router: Router
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.${class}Service.getQueryList(this.q.param1, this.q.param2)
      .subscribe((res: any) => {
        this.data = res.list;
        this.loading = false
        this.cdr.detectChanges();
      });
  }

  stChange(e: STChange) {
    switch (e.type) {
      case 'checkbox':
        this.selectedRows = e.checkbox!;
        this.totalCallNo = this.selectedRows.reduce((total, cv) => total + cv.callNo, 0);
        this.cdr.detectChanges();
        break;
      case 'filter':
        this.getData();
        break;
    }
  }

  remove(id: number) {
    this.${class}Service.delete(id).subscribe(() => {
      this.getData();
      this.st.clearCheck();
    });
  }
  create() {
    this.router.navigate(['/${class}/${class}detail'])
  }
  add(tpl: TemplateRef<{}>) {
    this.modalSrv.create({
      nzTitle: '',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        this.${class}Service.create(this.${class})
          .subscribe(() => this.getData());
      },
    });
  }

  reset() {
    // wait form reset updated finished
    this.q.param1 = '';
    this.q.param2 = '';
    this.getData()
  }

  showDeleteConfirm(id: number): void {
    this.modalSrv.confirm({
      nzTitle: '你确定要删除?',
      nzContent: '<b style="color: red;">此操作不可逆</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        console.log('OK')
        this.remove(id)

      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

}
