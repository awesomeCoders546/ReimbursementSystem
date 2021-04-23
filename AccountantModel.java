package com.sjp.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="accountant")
public class AccountantModel
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="accountant_id")
	private int accountantId;
	private String name;
	private String email;
	private String password;
	private String mobile;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	public int getAccountantId() {
		return accountantId;
	}
	public void setAccountantId(int accountantId) {
		this.accountantId = accountantId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public AccountantModel(int accountantId, String name, String email, String password, String mobile,
			Date createdDate) {
		super();
		this.accountantId = accountantId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.createdDate = createdDate;
	}
	public AccountantModel() {
		super();
	}
	

}
