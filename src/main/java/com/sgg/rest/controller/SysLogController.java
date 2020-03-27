package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.model.SysLog;
import com.sgg.rest.service.SysLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"日志"},description = "日志接口")
@RestController
@RequestMapping("/sysLog")
public class SysLogController {
	@Autowired  
    private SysLogService sysLogService;
	   @ApiOperation(value = "日志列表查询",notes = "无分页")
	    @ApiImplicitParams({
	        @ApiImplicitParam(required = false, paramType ="query", name = "fromTime", value = "开始时间"),
	        @ApiImplicitParam(required = false, paramType ="query", name = "toTime", value = "结束时间"),
	})
		@RequestMapping(value="/queryList", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> queryLog(@RequestParam(value="fromTime",required=false ) String fromTime, 
			@RequestParam(value="toTime",required=false ) String toTime) {
			Map<String,Object> map = new HashMap<String,Object>();
			List<SysLog> sysLogList = sysLogService.selectLogList(fromTime,toTime);
			map.put("list", sysLogList);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
