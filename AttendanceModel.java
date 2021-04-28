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
@Table(name="attendance_model")
public class AttendanceModel
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="attendence_id")
	private int attendenceId;
	@Column(name="employee_id")
	private long employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Column(name = "login_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	@Column(name = "logout_time",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	//@Temporal
	private String logoutTime;
	private String status;
	private String comment;
	private int logHours;
	
	public int getLogHours() {
		return logHours;
	}
	public void setLogHours(int logHours) {
		this.logHours = logHours;
	}
	
	public AttendanceModel(int attendenceId, int employeeId, String employeeName, Date createdDate, Date loginTime,
			String logoutTime, String status, String comment, int logHours) {
		super();
		this.attendenceId = attendenceId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.createdDate = createdDate;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.status = status;
		this.comment = comment;
		this.logHours = logHours;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public AttendanceModel() {
		super();
	}
	public int getAttendenceId() {
		return attendenceId;
	}
	public void setAttendenceId(int attendenceId) {
		this.attendenceId = attendenceId;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "AttendanceModel [attendenceId=" + attendenceId + ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", createdDate=" + createdDate + ", status=" + status + ", comment=" + comment + "]";
	}

}
