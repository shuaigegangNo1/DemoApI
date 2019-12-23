package com.sgg.rest.dto;

import java.util.List;

public class SecondMenuDto extends BaseMenuDto {
	private String icon;
	private List<ThirdMenuDto> children;
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<ThirdMenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<ThirdMenuDto> children) {
		this.children = children;
	}
	
}
