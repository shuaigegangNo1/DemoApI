import { BaseModule } from './BaseModule';

export class SysOrganization extends BaseModule {
	  		orgNo?: string;
	  		orgName?: string;
	  		orgAbr?: string;
	  		orgType?: string;
	  		orgStatus?: string;
	  		orgParentNo?: string;
	  		sort?: string;
	  		remark?: string;
	  		orgCreateTime?: Date;
	  		orgStopTime?: Date;
}