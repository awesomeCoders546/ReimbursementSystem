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
@Table(name="department")
public class DepartmentModel
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="department_id")
	private long departmentId;
	@Column(name="department_name")
	private String departmentName;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public DepartmentModel(long departmentId, String departmentName, Date createdDate) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.createdDate = createdDate;
	}
	public DepartmentModel() {
		super();
	}


}
