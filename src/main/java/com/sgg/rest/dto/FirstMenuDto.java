package com.sgg.rest.dto;

import java.util.List;


public class FirstMenuDto extends BaseMenuDto {

	private boolean group = true;
	private boolean hideInBreadcrumb = true;
	private List<SecondMenuDto> children;
	public boolean isGroup() {
		return group;
	}
	public void setGroup(boolean group) {
		this.group = group;
	}
	public boolean isHideInBreadcrumb() {
		return hideInBreadcrumb;
	}
	public void setHideInBreadcrumb(boolean hideInBreadcrumb) {
		this.hideInBreadcrumb = hideInBreadcrumb;
	}
	public List<SecondMenuDto> getChildren() {
		return children;
	}
	public void setChildren(List<SecondMenuDto> children) {
		this.children = children;
	}
	
}
