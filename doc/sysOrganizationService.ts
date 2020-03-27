import { Injectable } from "@angular/core";
import { DOMAIN_SERVER_URL } from "../constants";
import { _HttpClient } from '@delon/theme';
@Injectable()
export class SysOrganizationService {
    constructor(private http: _HttpClient) {

    }

    getServiceUrl(): string {
        return DOMAIN_SERVER_URL + '/sysOrganization';
    }

    create(sysOrganization: any) {
        return this.http.post(this.getServiceUrl() + '/create', sysOrganization)
    }
    update(sysOrganization: any) {
        return this.http.post(this.getServiceUrl() + '/update', sysOrganization)
    }
    delete(sysOrganizationId: number) {
        return this.http.get(this.getServiceUrl() + '/delete?sysOrganizationId=' + sysOrganizationId)
    }
    detail(sysOrganizationId: number) {
        return this.http.get(this.getServiceUrl() + '/detail?id=' + sysOrganizationId)
    }
    getQueryList(param1: string, param2: string) {
        return this.http.get(this.getServiceUrl() + '/queryList', { param1: param1, param2: param2 })
    }
}