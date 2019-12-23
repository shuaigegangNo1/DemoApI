package com.sgg.rest.dto;

import java.util.List;

public class OrgDto {
	private Integer id;
	private String orgName;
	private String orgAbr;
	private List<OrgDto> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAbr() {
		return orgAbr;
	}
	public void setOrgAbr(String orgAbr) {
		this.orgAbr = orgAbr;
	}
	public List<OrgDto> getChildren() {
		return children;
	}
	public void setChildren(List<OrgDto> children) {
		this.children = children;
	}

}
