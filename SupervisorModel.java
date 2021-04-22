package com.sjp.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="supervisor")
public class SupervisorModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="supervisor_id")
	private long supervisorId;
	@Column(name="supervisor_name")
	private String supervisorName;
	@Column(name="supervisor_salary")
	private String supervisorSalary;
	@Column(name="total_employees_under")
	private int totalEmployeesUnder;
	@Column(name="department_id")
	private long departmentId;
	private String email;
	private String password;
	private String mobile;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name ="supervisor_id", nullable = false)
	private List<EmployeeModel> employees;
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getSupervisorSalary() {
		return supervisorSalary;
	}
	public void setSupervisorSalary(String supervisorSalary) {
		this.supervisorSalary = supervisorSalary;
	}
	public int getTotalEmployeesUnder() {
		return totalEmployeesUnder;
	}
	public void setTotalEmployeesUnder(int totalEmployeesUnder) {
		this.totalEmployeesUnder = totalEmployeesUnder;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
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
	public List<EmployeeModel> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}
	public SupervisorModel(long supervisorId, String supervisorName, String supervisorSalary,
			int totalEmployeesUnder, long departmentId, String email, String password, String mobile,
			 Date createdDate, List<EmployeeModel> employees) {
		super();
		this.supervisorId = supervisorId;
		this.supervisorName = supervisorName;
		this.supervisorSalary = supervisorSalary;
		this.totalEmployeesUnder = totalEmployeesUnder;
		this.departmentId = departmentId;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		
		this.createdDate = createdDate;
		this.employees = employees;
	}
	public SupervisorModel() {
		super();
	}
    
}
