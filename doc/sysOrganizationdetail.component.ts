import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { ActivatedRoute, Router } from '@angular/router';
import { SysOrganization } from 'src/app/common/model/SysOrganization';
import { SysOrganizationService } from 'src/app/common/service/sysOrganizationService';


@Component({
    selector: 'app-sysOrganization-detail',
    templateUrl: './sysOrganization-detail.component.html',
})
export class SysOrganizationDetailComponent implements OnInit {
    form: FormGroup;
    sysOrganization: SysOrganization = new SysOrganization();
    id: any;
    constructor(private fb: FormBuilder, private msg: NzMessageService, private cdr: ChangeDetectorRef,
        public activedRoute: ActivatedRoute, private router: Router, private sysOrganizationService: SysOrganizationService) {
        this.activedRoute.queryParams.subscribe(params => {
            this.id = params['id'];
        });
        if (this.id) {
            this.sysOrganizationService.detail(this.id)
                .subscribe((res: any) => {
                    this.sysOrganization = res.data;
                });
        }
    }

    ngOnInit(): void {
        this.form = this.fb.group({
          		orgNo:  [null, []],
          		orgName:  [null, []],
          		orgAbr:  [null, []],
          		orgType:  [null, []],
          		orgStatus:  [null, []],
          		orgParentNo:  [null, []],
          		sort:  [null, []],
          		remark:  [null, []],
          		createTime: [null, [Validators.required]],
          		creator:  [null, []],
          		updateTime:  [null, []],
          		updater:  [null, []],
          		orgCreateTime: [null, [Validators.required]],
          		orgStopTime:  [null, []],
        });
    }

    transFormDateTimeStr(d: Date) {
        var datetimeStr = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
            + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        return datetimeStr;
    }
    update() {
        this.sysOrganizationService.update(this.sysOrganization).subscribe(res => {
            if (res.data) {
                this.router.navigate(['/sysOrganization/sysOrganizationlist'])
            }
        })
    }
    goback() {
        window.history.back();
    }
}
