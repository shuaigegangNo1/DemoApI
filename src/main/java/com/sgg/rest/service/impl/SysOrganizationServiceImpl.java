package com.sgg.rest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * author name: syg
 * create time: 2019-12-17 15:14:56
 */ 
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.SysOrganizationDao;
import com.sgg.rest.dto.OrgDto;
import com.sgg.rest.service.SysOrganizationService;
import com.sgg.rest.util.StringUtils;
import com.sgg.rest.model.SysOrganization;
@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationDao, SysOrganization>implements SysOrganizationService{
    @Autowired
    private SysOrganizationDao sysOrganizationDao;
	@Override
	public List<OrgDto> selectOrgList(Integer orgId) {
	    List<OrgDto> orgList = sysOrganizationDao.selectOrgListByOrgId(orgId);
		return orgList;
	}
	@Override
	public boolean createOrg(Integer orgParentNo, String orgName, String orgAbr, Integer orgStatus, String orgNo,
			Integer sort, Date orgCreateTime, Date orgStopTime) {
		SysOrganization sysOrg = new SysOrganization();
		sysOrg.setOrgName(orgName);
		sysOrg.setOrgParentNo(orgParentNo+"");
		sysOrg.setOrgAbr(orgAbr);
		sysOrg.setOrgNo(orgNo);
		sysOrg.setSort(sort+"");
		if(StringUtils.isNotBlank(orgCreateTime+"")) {
			sysOrg.setOrgCreateTime(orgCreateTime);
		}
		if(StringUtils.isNotBlank(orgStopTime+"")) {
			sysOrg.setOrgStopTime(orgStopTime);
		}
		if(StringUtils.isNotBlank(orgStatus+"")) {
			sysOrg.setOrgStatus(orgStatus+"");
		}
		boolean res =this.insert(sysOrg);
		return res;
	}
	@Override
	public List<SysOrganization> selectOrgQueryList(String orgName, String orgAbr) {
	    List<SysOrganization> orgList = sysOrganizationDao.selectOrgQueryList(orgName,orgAbr);
		return orgList;
	}


}

