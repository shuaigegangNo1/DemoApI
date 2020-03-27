package com.sgg.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * table name:  sys_role
 * author name: syg
 * create time: 2019-10-12 09:39:51
 */ 
@Data
public class SysRole{

	private Integer Id;

	private Integer type;

	private String roleName;

	private String remark;

	private Integer createUserId;
    @JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date createTime;

//	public Integer getId() {
//		return Id;
//	}
//	public void setId(Integer id) {
//		Id = id;
//	}
//	public void setType(Integer Type){
//		this.type=Type;
//	}
//	public Integer getType(){
//		return type;
//	}
//	public void setRoleName(String RoleName){
//		this.roleName=RoleName;
//	}
//	public String getRoleName(){
//		return roleName;
//	}
//	public void setRemark(String Remark){
//		this.remark=Remark;
//	}
//	public String getRemark(){
//		return remark;
//	}
//	public void setCreateUserId(Integer CreateUserId){
//		this.createUserId=CreateUserId;
//	}
//	public Integer getCreateUserId(){
//		return createUserId;
//	}
//	public void setCreateTime(Date CreateTime){
//		this.createTime=CreateTime;
//	}
//	public Date getCreateTime(){
//		return createTime;
//	}
}

