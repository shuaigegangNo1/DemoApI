package com.sgg.rest.dto;

public class ThirdMenuDto extends BaseMenuDto {
	private String link;
	//角色权限控制
	private String acl;
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
