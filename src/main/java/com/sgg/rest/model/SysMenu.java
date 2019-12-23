package com.sgg.rest.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
/**
 * table name:  sys_menu
 * author name: syg
 * create time: 2019-10-12 09:39:51
 */ 
public class SysMenu{

	private Integer id;

	private Integer parentId;

	private String text;

	private String i18n;

	private String link;

	private String icon;

	private Integer orderNum;

	private String creator;

	private String updater;

    /**
    * createTime
    * 创建时间
    */
    private Date createTime;
    /**
    * updateTime
    * 更新时间
    */
    @TableField(update="now()")
    private Date updateTime;
    /**
    * deleted
    * 删除标识位: 0正常 1删除
    */
	private Integer isDelete;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setParentId(Integer ParentId){
		this.parentId=ParentId;
	}
	public Integer getParentId(){
		return parentId;
	}
	public void setText(String Text){
		this.text=Text;
	}
	public String getText(){
		return text;
	}
	public void setI18n(String I18n){
		this.i18n=I18n;
	}
	public String getI18n(){
		return i18n;
	}
	public void setLink(String Link){
		this.link=Link;
	}
	public String getLink(){
		return link;
	}
	public void setIcon(String Icon){
		this.icon=Icon;
	}
	public String getIcon(){
		return icon;
	}
	public void setOrderNum(Integer OrderNum){
		this.orderNum=OrderNum;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}

