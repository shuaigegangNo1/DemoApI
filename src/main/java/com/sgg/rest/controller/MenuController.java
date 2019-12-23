package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sgg.rest.dto.BaseMenuDto;
import com.sgg.rest.dto.FirstMenuDto;
import com.sgg.rest.dto.SecondMenuDto;
import com.sgg.rest.dto.ThirdMenuDto;
import com.sgg.rest.model.SysMenu;
import com.sgg.rest.model.User;
import com.sgg.rest.service.SysMenuService;
import com.sgg.rest.util.DeletedEnum;
import com.sgg.rest.util.PageRequest;
import com.sgg.rest.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"菜单"},description = "菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired  
    private SysMenuService sysMenuService;  
    /**
     * 获取区域列表
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "获取菜单")
    public ResponseEntity<Map<String,Object>> getMenuList(@RequestParam("roleId")  Integer roleId) {
    	Map<String,Object> map = new HashMap<String,Object>();
        List<FirstMenuDto> firstMenuList = sysMenuService.selectFirstMenuList(roleId);
        	for(FirstMenuDto firstMenuDto :firstMenuList ) {
        		List<SecondMenuDto> secondMenuList = sysMenuService.selectScondMenuList(firstMenuDto.getId());
        			for(SecondMenuDto secondMenuDto :secondMenuList) {
        				List<ThirdMenuDto> thirdMenuList = sysMenuService.selectThirdMenuList(secondMenuDto.getId());
        				secondMenuDto.setChildren(thirdMenuList);
        			}
        		firstMenuDto.setChildren(secondMenuList);
        	}
        map.put("data", firstMenuList);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
    
	 @ApiOperation(value = "菜单分页查询",notes = "页码和页面大小都必须为整数")
	    @ApiImplicitParams({
@ApiImplicitParam(name = "pageQuery", value = "查询实体", required = true, dataType = "PageRequest")
})
	@RequestMapping(value="/findAll", method= RequestMethod.POST)
	    public ResponseEntity<Map<String,Object>>  selectMenus(@RequestBody PageRequest pageQuery){
		 	Map<String,Object> map = new HashMap<String,Object>();
	        Page<FirstMenuDto> pageInfo = sysMenuService.selectFirstMenuPage(pageQuery.getPageNum(), pageQuery.getPageSize());
	        map.put("data",pageInfo);
	        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	 }
	 
	 @ApiOperation(value = "修改菜单",notes = "id不能修改")
	    @ApiImplicitParams({
	        @ApiImplicitParam(name = "sysMenu", value = "菜单实体", required = true, dataType = "SysMenu")
	  })
	@RequestMapping(value="/update", method= RequestMethod.POST)
public ResponseEntity<Map<String,Object>> updateMenu(@RequestBody SysMenu sysMenu) {
		Map<String,Object> map = new HashMap<String,Object>();
		SysMenu f_sysMenu = sysMenuService.selectById(sysMenu.getId());
		boolean res = true;
		if(f_sysMenu ==null) {
			res = false;
		}
		 res =sysMenuService.updateById(sysMenu);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
} 
	 @ApiOperation(value = "创建菜单",notes = "")
	    @ApiImplicitParams({
	    	 @ApiImplicitParam(name = "sysMenu", value = "菜单实体", required = true, dataType = "SysMenu")
	  })
	@RequestMapping(value="/create", method= RequestMethod.POST)
public ResponseEntity<Map<String,Object>> createMenu(@RequestBody SysMenu sysMenu) {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean res = sysMenuService.insert(sysMenu);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
} 
	 
	    @ApiOperation(value = "菜单列表查询",notes = "无分页")
	    @ApiImplicitParams({
	        @ApiImplicitParam(required = false, paramType ="query", name = "parentId", value = "菜单级别"),
	        @ApiImplicitParam(required = false, paramType ="query", name = "text", value = "菜单名称"),
	})
		@RequestMapping(value="/queryList", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> queryPage(@RequestParam(value="parentId",required=false ) Integer parentId, 
			@RequestParam(value="text",required=false ) String text) {
			Map<String,Object> map = new HashMap<String,Object>();
		   	EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
		   	if(StringUtils.isNotBlank(parentId+"")) {
		   		wrapper.eq("parent_id", parentId);
		   	}
		   	if(StringUtils.isNotBlank(text)) {
			   	wrapper.like("text", text);
		   	}
		   	wrapper.eq("is_delete",DeletedEnum.N.value() );
		   	wrapper.orderBy("id", true);
			map.put("list", sysMenuService.selectList(wrapper));
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	    @ApiOperation(value = "一级菜单列表",notes = "无分页")
		@RequestMapping(value="/firstMenuList", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getFirstMenuList() {
			Map<String,Object> map = new HashMap<String,Object>();
			List<BaseMenuDto> list = sysMenuService.selectFirstMenuDtoList();
			map.put("data", list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
		 @ApiOperation(value = "批量创建菜单",notes = "")
		    @ApiImplicitParams({
		    	 @ApiImplicitParam(name = "sysMenuList", value = "菜单实体列表", required = true, dataType = "List<SysMenu>")
		  })
		@RequestMapping(value="/createBatch", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> createBatchMenu(@RequestBody List<SysMenu> sysMenuList) {
			Map<String,Object> map = new HashMap<String,Object>();
			boolean res = sysMenuService.insertBatch(sysMenuList);
			map.put("data", res);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	} 
		 @ApiOperation(value = "删除菜单",notes = "")
		    @ApiImplicitParams({
		        @ApiImplicitParam(required = true, paramType ="query", name = "menuId", value = "菜单Id")
		})
		@RequestMapping(value="/delete", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> deleteMenu(Integer menuId) {
			Map<String,Object> map = new HashMap<String,Object>();
			SysMenu menu = sysMenuService.selectById(menuId);
			boolean res = true;
			if(menu ==null) {
				res =false;
				map.put("data", res);
				map.put("msg", "菜单不存在，删除失败");
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			menu.setIsDelete(DeletedEnum.Y.value());
			res = sysMenuService.updateById(menu);
			if(res) {
				map.put("msg", "删除成功");
			}else {
				map.put("msg", "删除失败");
			}
			map.put("data", res);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	} 
		 @ApiOperation(value = "菜单详情",notes = "")
			@RequestMapping(value="/detail", method= RequestMethod.GET)
		public ResponseEntity<Map<String,Object>> getSysMenu(Integer id) {
				Map<String,Object> map = new HashMap<String,Object>();
				SysMenu menu = sysMenuService.selectById(id);
				map.put("data", menu);
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
}
