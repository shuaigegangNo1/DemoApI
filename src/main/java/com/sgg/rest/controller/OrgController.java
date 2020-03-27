package com.sgg.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.annotation.MyLog;
import com.sgg.rest.dto.OrgDto;
import com.sgg.rest.model.SysOrganization;
import com.sgg.rest.model.User;
import com.sgg.rest.service.SysOrganizationService;
import com.sgg.rest.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"机构"},description = "机构接口")
@RestController
@RequestMapping("/org")
public class OrgController {
	@Autowired  
    private SysOrganizationService orgService;
    /**
     * 获取机构列表
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "获取机构列表")
	@MyLog(value = "获取机构列表")
    public ResponseEntity<Map<String,Object>> getOrgList(@RequestParam("orgId")  Integer orgId) {
    	Map<String,Object> map = new HashMap<String,Object>();
        List<OrgDto> orgList = orgService.selectOrgList(orgId);
        	for(OrgDto orgDto :orgList ) {
        		List<OrgDto> secondOrgDtoList = orgService.selectOrgList(orgDto.getId());
        			for(OrgDto orgDto2 :secondOrgDtoList) {
        				List<OrgDto> thirdOrgDtoList = orgService.selectOrgList(orgDto2.getId());
        				orgDto2.setChildren(thirdOrgDtoList);
        				orgDto2.setExpanded(true);
        			}
        		orgDto.setChildren(secondOrgDtoList);
        		orgDto.setExpanded(true);
        	}
        map.put("data", orgList);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
    @ApiOperation(value = "创建机构",notes = "")
    @MyLog(value = "创建机构")
    @ApiImplicitParams({
        @ApiImplicitParam(required = false, paramType ="query", name = "orgParentNo", value = "上级机构",dataType = "int"),
        @ApiImplicitParam(required = true, paramType ="query", name = "orgName", value = "全称"),
        @ApiImplicitParam(required = true, paramType ="query", name = "orgAbr", value = "简称"),
        @ApiImplicitParam(required = false, paramType ="query", name = "orgStatus", value = "状态",dataType = "int"),
        @ApiImplicitParam(required = true, paramType ="query", name = "orgNo", value = "编号"),
        @ApiImplicitParam(required = true, paramType ="query", name = "sort", value = "序号",dataType = "int"),
        @ApiImplicitParam(required = true, paramType ="query", name = "orgCreateTime", value = "开创时间",dataType = "date"),
        @ApiImplicitParam(required = false, paramType ="query", name = "orgStopTime", value = "停业时间",dataType = "date"),
})
	@RequestMapping(value="/create1", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> updatePasswd
		(@RequestParam(value="orgParentNo",required=false ) Integer orgParentNo, @RequestParam(value="orgAbr",required=true ) String orgAbr,@RequestParam(value="orgName",required=true ) String orgName, 
		@RequestParam(value="orgStatus",required=false ) Integer orgStatus, @RequestParam(value="orgNo",required=true ) String orgNo, @RequestParam(value="sort",required=false ) Integer sort,
		@RequestParam(value="orgCreateTime",required=false ) @DateTimeFormat Date  orgCreateTime,@RequestParam(value="orgStopTime",required=false ) Date orgStopTime) {
		Map<String,Object> map = new HashMap<String,Object>();
	   	if(StringUtils.isNotBlank(orgParentNo+"")) {
	   	}else {
	   		orgParentNo = 0;
	   	}
//	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String dateString = formatter.format(orgCreateTime);

		boolean res =orgService.createOrg(orgParentNo,orgName,orgAbr,orgStatus,orgNo,sort,orgCreateTime,orgStopTime);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
	@RequestMapping(value="/create", method= RequestMethod.POST)
public ResponseEntity<Map<String,Object>> createOrg(@RequestBody SysOrganization sysOrganization) {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean res = orgService.insert(sysOrganization);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
} 
	
    /**
     * 获取机构列表
     * @return
     */
    @GetMapping(value = "/querylist")
    @ApiOperation(value = "获取机构列表")
	@MyLog(value = "获取机构列表")
    @ApiImplicitParams({
        @ApiImplicitParam(required = false, paramType ="query", name = "orgName", value = "全称"),
        @ApiImplicitParam(required = false, paramType ="query", name = "orgAbr", value = "简称")
})
    public ResponseEntity<Map<String,Object>> getOrgQueryList(@RequestParam(value="orgName",required=false ) String orgName, @RequestParam(value="orgAbr",required=false ) String orgAbr) {
    	Map<String,Object> map = new HashMap<String,Object>();
        List<SysOrganization> orgList = orgService.selectOrgQueryList(orgName,orgAbr);
        map.put("data", orgList);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
}
