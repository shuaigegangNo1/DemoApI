package com.sgg.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * table name:  sys_log
 * author name: syg
 * create time: 2019-12-13 16:18:52
 */ 
public class SysLog{

	private Integer id;

	/*用户名*/
	private String userName;

	/*操作*/
	private String operation;

	/*方法名*/
	private String method;

	/*参数*/
	private String params;

	/*主机地址*/
	private String ip;

	/*创建时间*/
    @JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date createTime;


	public void setId(Integer Id){
		this.id=Id;
	}
	public Integer getId(){
		return id;
	}
	public void setUserName(String UserName){
		this.userName=UserName;
	}
	public String getUserName(){
		return userName;
	}
	public void setOperation(String Operation){
		this.operation=Operation;
	}
	public String getOperation(){
		return operation;
	}
	public void setMethod(String Method){
		this.method=Method;
	}
	public String getMethod(){
		return method;
	}
	public void setParams(String Params){
		this.params=Params;
	}
	public String getParams(){
		return params;
	}
	public void setIp(String Ip){
		this.ip=Ip;
	}
	public String getIp(){
		return ip;
	}
	public void setCreateTime(Date CreateTime){
		this.createTime=CreateTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
}
