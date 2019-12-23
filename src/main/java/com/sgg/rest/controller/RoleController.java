package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sgg.rest.model.SysRole;
import com.sgg.rest.service.SysRoleService;
import com.sgg.rest.util.DeletedEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"角色"},description = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	 @ApiOperation(value = "角色列表",notes = "无分页")
	@RequestMapping(value="/getRoleList", method= RequestMethod.GET)
 public ResponseEntity<Map<String,Object>> findPage() {
		Map<String,Object> map = new HashMap<String,Object>();
    	EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
//    	wrapper.eq("deleted",DeletedEnum.N.value());
    	wrapper.orderBy("id", false);
		map.put("list", sysRoleService.selectList(wrapper));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
 }
}
