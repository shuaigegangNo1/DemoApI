import { Component, OnInit, ViewChild, TemplateRef, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { STComponent, STColumn, STData, STChange } from '@delon/abc';
import { SysOrganization } from 'src/app/common/model/SysOrganization';
import { SysOrganizationService } from 'src/app/common/service/sysOrganizationService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sysOrganization-list',
  templateUrl: './sysOrganizationlist.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SysOrganizationListComponent implements OnInit {
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
  sysOrganization: SysOrganization = new SysOrganization();
  @ViewChild('st', { static: true }) st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '机构编号', index: 'orgNo' },
    { title: '机构全称', index: 'orgName' },
    { title: '机构简称', index: 'orgAbr' },
    { title: '机构类型', index: 'orgType' },
    { title: '机构状态,1未营业、2正常营业、3暂停营业、4终止营业、5已除名', index: 'orgStatus' },
    { title: '机构父节点编号', index: 'orgParentNo' },
    { title: '机构显示顺序', index: 'sort' },
    { title: '备注', index: 'remark' },
    { title: '机构创建时间', index: 'orgCreateTime' },
    { title: '机构停运时间', index: 'orgStopTime' },
    {
      title: '操作',
      buttons: [
        {
          text: '详情',
          click: (item: any) => {
            this.router.navigate(['/sysOrganization/sysOrganizationdetail'], { queryParams: { id: item.id } })
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
    private sysOrganizationService: SysOrganizationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.sysOrganizationService.getQueryList(this.q.param1, this.q.param2)
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
    this.sysOrganizationService.delete(id).subscribe(() => {
      this.getData();
      this.st.clearCheck();
    });
  }
  create() {
    this.router.navigate(['/sysOrganization/sysOrganizationdetail'])
  }
  add(tpl: TemplateRef<{}>) {
    this.modalSrv.create({
      nzTitle: '',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        this.sysOrganizationService.create(this.sysOrganization)
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
