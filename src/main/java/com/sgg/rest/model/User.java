package com.sgg.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity // This tells Hibernate to make a table out of this class
//@ApiModel
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @ApiModelProperty(value = "用户id", name = "id", required = false)
    private Integer id;
//    @ApiModelProperty(value = "用户名称", name = "name", required = true,example = "张飒")
    private String name;
//    @ApiModelProperty(value = "邮箱", name = "email", required = true,example = "XXX.sgg@com")
    private String email;
//    @ApiModelProperty(value = "用户名称", name = "password", required = true,example = "xxx")
    private String password;
    private Integer roleId;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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




	
//	@Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}