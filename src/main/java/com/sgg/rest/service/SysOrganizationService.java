package com.sgg.rest.service;

import java.util.Date;
import java.util.List;

/**
 * author name: syg
 * create time: 2019-12-17 15:14:56
 */ 
import com.baomidou.mybatisplus.service.IService;
import com.sgg.rest.dto.OrgDto;
import com.sgg.rest.model.SysOrganization;
public interface SysOrganizationService extends IService<SysOrganization>{

	List<OrgDto> selectOrgList(Integer orgId);

	boolean createOrg(Integer orgParentNo, String orgName, String orgAbr, Integer orgStatus, String orgNo, Integer sort,
			Date orgCreateTime, Date orgStopTime);

	List<SysOrganization> selectOrgQueryList(String orgName, String orgAbr);
}

