import { Component, OnInit, ViewChild, TemplateRef, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { STComponent, STColumn, STData, STChange } from '@delon/abc';
import { SysLog } from 'src/app/common/model/SysLog';
import { SysLogService } from 'src/app/common/service/sysLogService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sysLog-list',
  templateUrl: './sysLoglist.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SysLogListComponent implements OnInit {
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
  sysLog: SysLog = new SysLog();
  @ViewChild('st', { static: true }) st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '用户名', index: 'userName' },
    { title: '操作', index: 'operation' },
    { title: '方法名', index: 'method' },
    { title: '参数', index: 'params' },
    { title: '主机地址', index: 'ip' },
    {
      title: '操作',
      buttons: [
        {
          text: '详情',
          click: (item: any) => {
            this.router.navigate(['/sysLog/sysLogdetail'], { queryParams: { id: item.id } })
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
    private sysLogService: SysLogService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.sysLogService.getQueryList(this.q.param1, this.q.param2)
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
    this.sysLogService.delete(id).subscribe(() => {
      this.getData();
      this.st.clearCheck();
    });
  }
  create() {
    this.router.navigate(['/sysLog/sysLogdetail'])
  }
  add(tpl: TemplateRef<{}>) {
    this.modalSrv.create({
      nzTitle: '',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        this.sysLogService.create(this.sysLog)
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
