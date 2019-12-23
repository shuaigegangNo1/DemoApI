package com.sgg.rest.controller;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sgg.rest.annotation.MyLog;
import com.sgg.rest.enums.BusinessType;
//import com.github.pagehelper.IPage;
import com.sgg.rest.model.Account;
import com.sgg.rest.model.SysLog;
import com.sgg.rest.model.SysMenu;
import com.sgg.rest.model.SysRole;
import com.sgg.rest.model.User;
import com.sgg.rest.service.AccountService;
import com.sgg.rest.service.SysLogService;
import com.sgg.rest.service.SysMenuService;
import com.sgg.rest.service.SysRoleService;
import com.sgg.rest.service.UserService;
import com.sgg.rest.util.DeletedEnum;
import com.sgg.rest.util.PageRequest;
import com.sgg.rest.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


//@RestController
//@RequestMapping("/test") // This means URL's start with /user (after Application path)
//@Api(value = "测试管理")
//public class TestController {
//	@Autowired  
//	private AccountService accountService;
//	@RequestMapping("test")
//	public String test(ModelMap map){
//		map.put("title","用户列表");
//		map.put("users",parseUsers());
//	    return "test";
//	}
//	
//	   private List<Map> parseUsers(){
//	        List<Map> list= new ArrayList<>();
//	        for(int i=0;i<4;i++){
//	            Map map= new HashMap();
//	            map.put("name","kevin_"+i);
//	            map.put("age",10+i);
//	            map.put("phone","1860291105"+i);
//	            list.add(map);
//	        }
//	        return list;
//	    }
////		@GetMapping(path="/get/accountList") // Map ONLY GET Requests
////		public ResponseEntity<Map<String,Object>> getAccountList (Page page) {
////			Map<String,Object> map = new HashMap<String,Object>();
////			map.put("accountList",accountService.selectAccountList());
////
////	        Account account = new Account();
//////	        Page<Account> p = new Page<>(page, pageSize);
//////	        PageResult pageResult = accountService.queryPage(page);
//////	        map.put("page", pageResult);
////			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
////		}
//		
//		
//		@RequestMapping(value="/findPage", method= RequestMethod.POST)
//	    public ResponseEntity<Map<String,Object>> findPage(@RequestBody PageRequest pageQuery) {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("list", accountService.findPage(pageQuery));
//			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//	    }
//	    @GetMapping(value = "/find")
//	    @ApiOperation(value = "测试")
//	    @ApiImplicitParams({
//	            @ApiImplicitParam(required = true, paramType ="query", name = "type", value = "1：闪屏图 2：轮播图 3:广告")
//	    })
////		@RequestMapping(value="/find", method= RequestMethod.GET)
//	    public ResponseEntity<Map<String,Object>> find(@RequestParam String name) {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("list", name);
//			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//	    }
//}



@Api(tags = {"测试"},description = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {
//	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysLogService sysLogService;
    private static final Logger logger = LogManager.getLogger(TestController.class);
	@RequestMapping(method = RequestMethod.GET,value = "/hi")
    @ApiOperation(value = "问候语", notes = "这是一个问候")
    @ApiImplicitParams({
        @ApiImplicitParam(required = true, paramType ="query", name = "name", value = "姓名"),
        @ApiImplicitParam(required = true, paramType ="query", name = "age", value = "年龄")
})
    public String hello(@RequestParam("name")  String  name,@RequestParam("age")  Integer age) {
		logger.info(">>>>>>name>>>>>"+name);

        return "Hell World " + name + age;
    }
	
	 @ApiOperation(value = "修改用户信息",notes = "直接使用名字修改，名字不能改！")
	    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "ApplicationUser")
    })
	    @RequestMapping(value = "/modify",method = RequestMethod.POST)
	    public String getUser(@RequestBody User user){
	            return "success" + user.getName();
	        
	    }
	 
	 @ApiOperation(value = "新建账号",notes = "直接使用名字修改，名字不能改！")
	    @ApiImplicitParams({
         @ApiImplicitParam(name = "account", value = "用户实体", required = true, dataType = "Account")
 })
	    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
	    public String getUser(@RequestBody Account account){
//		 		 int res = accountService.createNewAccount(account);
	    	EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
	    	SysMenu sysMenu = sysMenuService.selectOne(wrapper.eq("menu_id",account.getId()));
//		 		 SysMenu SysMenu = sysMenuService.selectOne(wrapper);
	            return "success " + sysMenu.getText() +" ";
	        
	    }
	 @ApiOperation(value = "修改账号",notes = "直接使用名字修改，名字不能改！")
	    @ApiImplicitParams({
      @ApiImplicitParam(name = "account", value = "用户实体", required = true, dataType = "Account")
})
	    @RequestMapping(value = "/updateAccount",method = RequestMethod.POST)
	    public String updateAccount(@RequestBody Account account){
//		 		 int res = accountService.createNewAccount(account);
	    	EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
	    	SysRole sysRole = sysRoleService.selectById(37);
//	    	SysMenu sysMenu = sysMenuService.selectOne(wrapper.eq("menu_id",account.getId()));
//		 		sysMenu.setLink("/88888/66666");
//		 		sysMenuService.update(sysMenu, wrapper);
	    	User user  = userService.findUserByName("hxw666");
	            return "success " + user.getId()+" ";
	        
	    }
//	@GetMapping(path="/get/accountList") // Map ONLY GET Requests
//	public ResponseEntity<Map<String,Object>> getAccountList (Page page) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("accountList",accountService.selectAccountList());
//
//     Account account = new Account();
////     Page<Account> p = new Page<>(page, pageSize);
////     PageResult pageResult = accountService.queryPage(page);
////     map.put("page", pageResult);
//		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//	}
//	 @ApiOperation(value = "账号查询",notes = "直接使用名字修改，名字不能改！")
//	    @ApiImplicitParams({
//      @ApiImplicitParam(name = "pageQuery", value = "查询实体", required = true, dataType = "PageRequest")
//})
//	@RequestMapping(value="/findPage", method= RequestMethod.POST)
//    public ResponseEntity<Map<String,Object>> findPage(@RequestBody PageRequest pageQuery) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("list", accountService.findPage(pageQuery));
//		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//    }
	 
	 @ApiOperation(value = "账号分页查询",notes = "页码和页面大小都必须为整数")
	    @ApiImplicitParams({
   @ApiImplicitParam(name = "pageQuery", value = "查询实体", required = true, dataType = "PageRequest")
})
//	 @MyLog(value = "删除菜单记录",businessType = BusinessType.INSERT)  //这里添加了AOP的自定义注解
	 @MyLog(value = "删除菜单记录")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/findAll", method= RequestMethod.POST)
	    public ResponseEntity<Map<String,Object>>  findAllUser(@RequestBody PageRequest pageQuery){
		 	Map<String,Object> map = new HashMap<String,Object>();
	        Page<Account> pageInfo = accountService.selectPageExt(pageQuery.getPageNum(), pageQuery.getPageSize());
	        map.put("data",pageInfo);
	        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
//	        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
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