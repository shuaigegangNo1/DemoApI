package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sgg.rest.dto.ChartDataDto;
import com.sgg.rest.dto.SignUpDataDto;
import com.sgg.rest.model.SysRole;
import com.sgg.rest.model.User;
import com.sgg.rest.service.SysRoleService;
import com.sgg.rest.service.UserService;
import com.sgg.rest.util.DeletedEnum;
import com.sgg.rest.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"用户"},description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @ApiOperation(value = "用户列表",notes = "无分页")
	@RequestMapping(value="/list", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> findPage() {
		Map<String,Object> map = new HashMap<String,Object>();
	   	EntityWrapper<User> wrapper = new EntityWrapper<>();
	   	wrapper.orderBy("id", true);
		map.put("list", userService.selectList(wrapper));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
	 @ApiOperation(value = "设定角色",notes = "")
	@RequestMapping(value="/updateRole", method= RequestMethod.GET)
 public ResponseEntity<Map<String,Object>> updateRole(Integer userId,Integer roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
    	User user = userService.selectById(userId);
    	boolean res;
    	if(user == null) {
    		res = false;
    	}
    	user.setRoleId(roleId);
    	res = userService.updateById(user);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
 }
	 @ApiOperation(value = "用户详情",notes = "")
	@RequestMapping(value="/detail", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> getUser(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.selectById(id);
		map.put("data", user);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
	 @ApiOperation(value = "修改用户",notes = "id不能修改")
	    @ApiImplicitParams({
	        @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	  })
	@RequestMapping(value="/update", method= RequestMethod.POST)
public ResponseEntity<Map<String,Object>> updateUser(@RequestBody User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		User f_user = userService.selectById(user.getId());
		boolean res = true;
		if(f_user ==null) {
			res = false;
		}
		userService.updateById(user);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
} 
	 @ApiOperation(value = "创建用户",notes = "id不能修改")
	    @ApiImplicitParams({
	        @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	  })
	@RequestMapping(value="/create", method= RequestMethod.POST)
public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		boolean res = userService.insert(user);
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
} 
	 
		@RequestMapping(value="/sign-up", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>>  getNewUser( @RequestBody User u) {
		Map<String,Object> map = new HashMap<String,Object>();
		User f_user = userService.findUserByName(u.getName());
		if (f_user!=null) {
			map.put("result", "user sign up failed,user exists");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		userService.updateById(u);
		map.put("result", "user sign up success");
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
    @ApiOperation(value = "用户列表查询",notes = "无分页")
    @ApiImplicitParams({
        @ApiImplicitParam(required = false, paramType ="query", name = "name", value = "姓名"),
        @ApiImplicitParam(required = false, paramType ="query", name = "email", value = "邮箱"),
})
	@RequestMapping(value="/queryList", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> queryPage(@RequestParam(value="name",required=false ) String name, 
		@RequestParam(value="email",required=false ) String email) {
		Map<String,Object> map = new HashMap<String,Object>();
	   	EntityWrapper<User> wrapper = new EntityWrapper<>();
	   	if(StringUtils.isNotBlank(name)) {
	   		wrapper.like("name", name);
	   	}
	   	if(StringUtils.isNotBlank(email)) {
		   	wrapper.like("email", email);
	   	}
	   	wrapper.eq("is_delete",DeletedEnum.N.value() );
	   	wrapper.orderBy("id", true);
		map.put("list", userService.selectList(wrapper));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
    
	 @ApiOperation(value = "每天用户注册数据",notes = "")

	@RequestMapping(value="/chart/signUpDataByDay", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> getSignUpData() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SignUpDataDto> list = userService.getSignUpData();
		map.put("list", list);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
	 @ApiOperation(value = "每月用户注册数据",notes = "")
	@RequestMapping(value="/chart/signUpDataByMonth", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getSignUpDataByMonth() {
			Map<String,Object> map = new HashMap<String,Object>();
			List<ChartDataDto> list = userService.getSignUpDataByMonth("2019");
		   	EntityWrapper<User> wrapper = new EntityWrapper<>();
		   	wrapper.eq("is_delete",DeletedEnum.N.value() );
		   	List<User> userList = userService.selectList(wrapper);
		   	EntityWrapper<SysRole> rwrapper = new EntityWrapper<>();
		   	List<SysRole> roleList = roleService.selectList(rwrapper);
			map.put("sum", userList.size());
			map.put("rsum", roleList.size());
//			for(ChartDataDto chartDataDto :list) {
////				chartDataDto.get
//			}
			map.put("list", list);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	} 
	 @ApiOperation(value = "删除用户",notes = "")
	    @ApiImplicitParams({
	        @ApiImplicitParam(required = true, paramType ="query", name = "userId", value = "用户Id")
	})
	@RequestMapping(value="/delete", method= RequestMethod.GET)
public ResponseEntity<Map<String,Object>> deleteUser(Integer userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = userService.selectById(userId);
		boolean res = true;
		if(user ==null) {
			res =false;
			map.put("data", res);
			map.put("msg", "用户不存在，删除失败");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		user.setIsDelete(DeletedEnum.Y.value());
		res = userService.updateById(user);
		if(res) {
			map.put("msg", "删除成功");
		}else {
			map.put("msg", "删除失败");
		}
		map.put("data", res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
}
	    @ApiOperation(value = "修改密码",notes = "")
	    @ApiImplicitParams({
	        @ApiImplicitParam(required = true, paramType ="query", name = "userId", value = "用户Id"),
	        @ApiImplicitParam(required = true, paramType ="query", name = "passwd", value = "原密码"),
	        @ApiImplicitParam(required = true, paramType ="query", name = "newPasswd", value = "新密码"),
	})
		@RequestMapping(value="/updatePasswd", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> updatePasswd(@RequestParam Integer userId, 
			@RequestParam String passwd,@RequestParam String newPasswd) {
			Map<String,Object> map = new HashMap<String,Object>();
			User f_user = userService.selectById(userId);
			boolean res = true;
			String msg = "";
			if(f_user==null) {
				res=false;
				msg="用户不存在";
				map.put("data", res);
				map.put("msg", msg);
				return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			}
			 res = bCryptPasswordEncoder.matches(passwd,f_user.getPassword());
			if(!res) {
				msg="原密码不正确";
			}else {
				msg="修改成功";
				f_user.setPassword(bCryptPasswordEncoder.encode(newPasswd));
				userService.updateById(f_user);
			}
			map.put("data", res);
			map.put("msg", msg);
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
