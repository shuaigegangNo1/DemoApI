package com.sgg.rest.model;

import java.util.Date;
/**
 * table name:  sys_user_role
 * author name: syg
 * create time: 2019-10-24 10:04:51
 */ 
public class SysUserRole{

	private Integer id;

	/*�û�ID*/
	private Integer userId;

	/*��ɫID*/
	private Integer roleId;


	public void setId(Integer Id){
		this.id=Id;
	}
	public Integer getId(){
		return id;
	}
	public void setUserId(Integer UserId){
		this.userId=UserId;
	}
	public Integer getUserId(){
		return userId;
	}
	public void setRoleId(Integer RoleId){
		this.roleId=RoleId;
	}
	public Integer getRoleId(){
		return roleId;
	}
}

