package com.sgg.rest.dto;

import java.util.List;

public class OrgNodeDto {
	private Integer id;
	private String key;
	private String title;
	private boolean isLeaf;
	private boolean expanded;
	private List<OrgNodeDto> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public List<OrgNodeDto> getChildren() {
		return children;
	}
	public void setChildren(List<OrgNodeDto> children) {
		this.children = children;
	}

}
