package com.qingshixun.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ssh_user")
public class UserModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int no;
	private String name;
	private String email;
	private String sex;
	private Date createDate;
	private Date updateDate;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public UserModel(int no, String name, String email, String sex, Date createDate, Date updateDate, String password) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.password = password;
	}
	public UserModel(String name, String email, String sex, Date createDate, Date updateDate, String password) {
		super();
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.password = password;
	}
	public UserModel(int no) {
		super();
		this.no = no;
	}
	public UserModel() {
		super();
	}
	@Override
	public String toString() {
		return "UserModel [no=" + no + ", name=" + name + ", email=" + email + ", sex=" + sex + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", password=" + password + "]";
	}
	
	
}
