package com.sgg.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * table name:  sys_organization
 * author name: syg
 * create time: 2019-12-17 15:14:56
 */ 
public class SysOrganization{

	private Integer id;

	private String orgNo;

	private String orgName;

	private String orgAbr;

	private String orgType;

	private String orgStatus;

	private String orgParentNo;

	private String sort;

	private String remark;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	private String creator;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	private String updater;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date orgCreateTime;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	private Date orgStopTime;


	public void setId(Integer Id){
		this.id=Id;
	}
	public Integer getId(){
		return id;
	}
	public void setOrgNo(String OrgNo){
		this.orgNo=OrgNo;
	}
	public String getOrgNo(){
		return orgNo;
	}
	public void setOrgName(String OrgName){
		this.orgName=OrgName;
	}
	public String getOrgName(){
		return orgName;
	}
	public void setOrgAbr(String OrgAbr){
		this.orgAbr=OrgAbr;
	}
	public String getOrgAbr(){
		return orgAbr;
	}
	public void setOrgType(String OrgType){
		this.orgType=OrgType;
	}
	public String getOrgType(){
		return orgType;
	}
	public void setOrgStatus(String OrgStatus){
		this.orgStatus=OrgStatus;
	}
	public String getOrgStatus(){
		return orgStatus;
	}
	public void setOrgParentNo(String OrgParentNo){
		this.orgParentNo=OrgParentNo;
	}
	public String getOrgParentNo(){
		return orgParentNo;
	}
	public void setSort(String Sort){
		this.sort=Sort;
	}
	public String getSort(){
		return sort;
	}
	public void setRemark(String Remark){
		this.remark=Remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setCreateTime(Date CreateTime){
		this.createTime=CreateTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreator(String Creator){
		this.creator=Creator;
	}
	public String getCreator(){
		return creator;
	}
	public void setUpdateTime(Date UpdateTime){
		this.updateTime=UpdateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdater(String Updater){
		this.updater=Updater;
	}
	public String getUpdater(){
		return updater;
	}
	public void setOrgCreateTime(Date OrgCreateTime){
		this.orgCreateTime=OrgCreateTime;
	}
	public Date getOrgCreateTime(){
		return orgCreateTime;
	}
	public void setOrgStopTime(Date OrgStopTime){
		this.orgStopTime=OrgStopTime;
	}
	public Date getOrgStopTime(){
		return orgStopTime;
	}
}

