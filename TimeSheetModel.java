package com.sjp.Model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="TimeSheet")
public class TimeSheetModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TimeSheet_id")
	private int timeSheetId;
	@Column(name="Employee_id")
	private long employeeId;
	@Column(name="Employee_Name")
	private String employeeName;
	private Date date;
	private String day;
	private String projectName;
	private String task;
	@Column(name="log_Hours")
	private int logHours;
	@Transient
	private int logWeekHours;
	public int getLogWeekHours() {
		return logWeekHours;
	}
	public void setLogWeekHours(int logWeekHours) {
		this.logWeekHours = logWeekHours;
	}
	@Transient
	private int logDayHours;
	public int getLogDayHours() {
		return logDayHours;
	}
	public void setLogDayHours(int logDayHours) {
		this.logDayHours = logDayHours;
	}
	@Transient
	private int logMonthHours;
	
	public int getLogMonthHours() {
		return logMonthHours;
	}
	public void setLogMonthHours(int logMonthHours) {
		this.logMonthHours = logMonthHours;
	}
	private long supervisor_id;
	private String supervisorApproved;
	private String accountantApproved;
	
	public String getSupervisorApproved() {
		return supervisorApproved;
	}
	public void setSupervisorApproved(String supervisorApproved) {
		this.supervisorApproved = supervisorApproved;
	}
	public String getAccountantApproved() {
		return accountantApproved;
	}
	public void setAccountantApproved(String accountantApproved) {
		this.accountantApproved = accountantApproved;
	}
	public TimeSheetModel() {
		super();
	}
	
	public int getTimeSheetId() {
		return timeSheetId;
	}
	public void setTimeSheetId(int timeSheetId) {
		this.timeSheetId = timeSheetId;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public int getLogHours() {
		return logHours;
	}
	public void setLogHours(int logHours) {
		this.logHours = logHours;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public long getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(long supervisor_id) {
		this.supervisor_id = supervisor_id;
	}
	
	
	
	
	

}