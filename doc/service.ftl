import { Injectable } from "@angular/core";
import { DOMAIN_SERVER_URL } from "../constants";
import { _HttpClient } from '@delon/theme';
@Injectable()
export class ${class?cap_first}Service {
    constructor(private http: _HttpClient) {

    }

    getServiceUrl(): string {
        return DOMAIN_SERVER_URL + '/${class}';
    }

    create(${class}: any) {
        return this.http.post(this.getServiceUrl() + '/create', ${class})
    }
    update(${class}: any) {
        return this.http.post(this.getServiceUrl() + '/update', ${class})
    }
    delete(${class}Id: number) {
        return this.http.get(this.getServiceUrl() + '/delete?${class}Id=' + ${class}Id)
    }
    detail(${class}Id: number) {
        return this.http.get(this.getServiceUrl() + '/detail?id=' + ${class}Id)
    }
    getQueryList(param1: string, param2: string) {
        return this.http.get(this.getServiceUrl() + '/queryList', { param1: param1, param2: param2 })
    }
}