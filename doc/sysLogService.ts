import { Injectable } from "@angular/core";
import { DOMAIN_SERVER_URL } from "../constants";
import { _HttpClient } from '@delon/theme';
@Injectable()
export class SysLogService {
    constructor(private http: _HttpClient) {

    }

    getServiceUrl(): string {
        return DOMAIN_SERVER_URL + '/sysLog';
    }

    create(sysLog: any) {
        return this.http.post(this.getServiceUrl() + '/create', sysLog)
    }
    update(sysLog: any) {
        return this.http.post(this.getServiceUrl() + '/update', sysLog)
    }
    delete(sysLogId: number) {
        return this.http.get(this.getServiceUrl() + '/delete?sysLogId=' + sysLogId)
    }
    detail(sysLogId: number) {
        return this.http.get(this.getServiceUrl() + '/detail?id=' + sysLogId)
    }
    getQueryList(param1: string, param2: string) {
        return this.http.get(this.getServiceUrl() + '/queryList', { param1: param1, param2: param2 })
    }
}