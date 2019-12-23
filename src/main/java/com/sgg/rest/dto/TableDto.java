package com.sgg.rest.dto;

public class TableDto {
	private String name;
	private String comment;
	
//	public TableDto(String name, String comment) {
//		this.name = name;
//		this.comment = comment;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
