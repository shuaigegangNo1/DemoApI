package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.dto.TableDto;
import com.sgg.rest.util.FtlUtil;
import com.sgg.rest.util.MySqlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"通用"},description = "通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {

	 @ApiOperation(value = "数据库表列表",notes = "无分页")
	@RequestMapping(value="/tableList", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> findPage(String ip,String db,String name,String passwd) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("data", new MySqlUtil().getTables(ip,db,name,passwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}		
	 	@ApiOperation(value = "数据库过滤表列表",notes = "无分页")
		@RequestMapping(value="/selectTableList", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> createBatchMenu(@RequestParam String ip,@RequestParam String db,@RequestParam String name,@RequestParam String passwd,
			@RequestBody List<String> dlist) {
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				List<TableDto> tables = new MySqlUtil().getTables(ip,db,name,passwd);
			for(int j =0;j<dlist.size();j++) {
				for(int i=0;i<tables.size();i++) {
					if(tables.get(i).getName().equals(dlist.get(j))) {
						tables.remove(i);
					}
				}
			}
				map.put("data", tables);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	} 
	 	
	 	
	 	@ApiOperation(value = "创建模板",notes = "")
		@RequestMapping(value="/createTemplate", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> createtTemplate(@RequestParam String ip,@RequestParam String db,@RequestParam String name,@RequestParam String passwd,@RequestParam int templateType,@RequestBody List<TableDto> tlist) throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			String outPath="D:/Projects/DemoAPI/doc/";
			for(int j =0;j<tlist.size();j++) {
				    String  tableName = tlist.get(j).getName();
				    String  tableIndex = tlist.get(j).getComment();

				    String capTableName = tableName.split("_").length>1?new FtlUtil().underlineToCamel(tableName):tableName;
				    Map<String,Object> dataTableInfo = new FtlUtil().generateData(ip,db,name,passwd,tableName,tableIndex);

				    switch (templateType) {
					case 1:
						 new FtlUtil().generate(dataTableInfo,"service.ftl",capTableName+"Service.ts",outPath);
						 new FtlUtil().generate(dataTableInfo,"model.ftl",capTableName+".ts",outPath);
						 new FtlUtil().generate(dataTableInfo,"detail_html.ftl",capTableName+"detail.component.html",outPath);
						 new FtlUtil().generate(dataTableInfo,"detail_ts.ftl",capTableName+"detail.component.ts",outPath);
						 new FtlUtil().generate(dataTableInfo,"list_html.ftl",capTableName+"list.component.html",outPath);
						 new FtlUtil().generate(dataTableInfo,"list_ts.ftl",capTableName+"list.component.ts",outPath);
						break;
					case 2:
						 new FtlUtil().generate(dataTableInfo,"service.ftl",capTableName+"Service.ts",outPath);
						break;
					case 3:
						 new FtlUtil().generate(dataTableInfo,"model.ftl",capTableName+".ts",outPath);
						break;
					case 4:
						 new FtlUtil().generate(dataTableInfo,"detail_html.ftl",capTableName+"detail.component.html",outPath);
						 new FtlUtil().generate(dataTableInfo,"detail_ts.ftl",capTableName+"detail.component.ts",outPath);
						break;
					case 5:
						 new FtlUtil().generate(dataTableInfo,"list_html.ftl",capTableName+"list.component.html",outPath);
						 new FtlUtil().generate(dataTableInfo,"list_ts.ftl",capTableName+"list.component.ts",outPath);
						break;
					default:
						break;
					}
				}
				map.put("data", "创建成功");
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	 	}
}
