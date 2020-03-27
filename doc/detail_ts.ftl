import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { ActivatedRoute, Router } from '@angular/router';
import { ${class?cap_first} } from 'src/app/common/model/${class?cap_first}';
import { ${class?cap_first}Service } from 'src/app/common/service/${class}Service';


@Component({
    selector: 'app-${class}-detail',
    templateUrl: './${class}-detail.component.html',
})
export class ${class?cap_first}DetailComponent implements OnInit {
    form: FormGroup;
    ${class}: ${class?cap_first} = new ${class?cap_first}();
    id: any;
    constructor(private fb: FormBuilder, private msg: NzMessageService, private cdr: ChangeDetectorRef,
        public activedRoute: ActivatedRoute, private router: Router, private ${class}Service: ${class?cap_first}Service) {
        this.activedRoute.queryParams.subscribe(params => {
            this.id = params['id'];
        });
        if (this.id) {
            this.${class}Service.detail(this.id)
                .subscribe((res: any) => {
                    this.${class} = res.data;
                });
        }
    }

    ngOnInit(): void {
        this.form = this.fb.group({
          <#list modelList as model>
          	<#if model.code!="id" && model.code!="isDelete" >   
          		${model.code}: <#if model.isNull=="NO">[null, [Validators.required]]<#else> [null, []]</#if>,
          	</#if>
          </#list>
        });
    }

    transFormDateTimeStr(d: Date) {
        var datetimeStr = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
            + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        return datetimeStr;
    }
    update() {
        this.${class}Service.update(this.${class}).subscribe(res => {
            if (res.data) {
                this.router.navigate(['/${class}/${class}list'])
            }
        })
    }
    goback() {
        window.history.back();
    }
}
