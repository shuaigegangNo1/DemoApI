package com.sgg.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sgg.rest.dto.OrgDto;
import com.sgg.rest.model.SysOrganization;
/**
 * author name: syg
 * create time: 2019-12-17 15:14:56
 */ 
@Mapper
public interface SysOrganizationDao extends BaseMapper<SysOrganization>{
	List<OrgDto> selectOrgListByOrgId(@Param("orgId") Integer orgId);
}

