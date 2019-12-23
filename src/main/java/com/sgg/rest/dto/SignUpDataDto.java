package com.sgg.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SignUpDataDto {
	//signUp Date
    @JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
	private Date x;
	//signUp Persons 
	private int y;
	public Date getX() {
		return x;
	}
	public void setX(Date x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
