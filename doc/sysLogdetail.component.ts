import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { ActivatedRoute, Router } from '@angular/router';
import { SysLog } from 'src/app/common/model/SysLog';
import { SysLogService } from 'src/app/common/service/sysLogService';


@Component({
    selector: 'app-sysLog-detail',
    templateUrl: './sysLog-detail.component.html',
})
export class SysLogDetailComponent implements OnInit {
    form: FormGroup;
    sysLog: SysLog = new SysLog();
    id: any;
    constructor(private fb: FormBuilder, private msg: NzMessageService, private cdr: ChangeDetectorRef,
        public activedRoute: ActivatedRoute, private router: Router, private sysLogService: SysLogService) {
        this.activedRoute.queryParams.subscribe(params => {
            this.id = params['id'];
        });
        if (this.id) {
            this.sysLogService.detail(this.id)
                .subscribe((res: any) => {
                    this.sysLog = res.data;
                });
        }
    }

    ngOnInit(): void {
        this.form = this.fb.group({
          		userName:  [null, []],
          		operation:  [null, []],
          		method:  [null, []],
          		params:  [null, []],
          		ip:  [null, []],
          		createTime: [null, [Validators.required]],
        });
    }

    transFormDateTimeStr(d: Date) {
        var datetimeStr = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
            + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        return datetimeStr;
    }
    update() {
        this.sysLogService.update(this.sysLog).subscribe(res => {
            if (res.data) {
                this.router.navigate(['/sysLog/sysLoglist'])
            }
        })
    }
    goback() {
        window.history.back();
    }
}
