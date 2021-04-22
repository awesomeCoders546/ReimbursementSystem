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
@Table(name="reimbursement_table")
public class ReimbursementModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimburce_id")
	private long reimburceId;
	@Column(name="employee_id")
	private long employeeId;
	@Column(name="food_expense")
	private long foodExpense;
	@Column(name="travel_expense")
	private long travelExpense;
	@Column(name="other_expense")
	private long otherExpenss;
	@Column(name="food_bill")
	private String foodBill;
	@Column(name="travel_bill")
	private String travelBill;
	@Column(name="other_bill")
	private String otherBill;
	@Column(name="supervisor_id")
	private long supervisorId;
	@Column(name="date_of_Reimburse")
	private Date dateOfReimburse;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="total_expense")
	private long totalExpense;
	public long getReimburceId() {
		return reimburceId;
	}
	public void setReimburceId(long reimburceId) {
		this.reimburceId = reimburceId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getFoodExpense() {
		return foodExpense;
	}
	public void setFoodExpense(long foodExpense) {
		this.foodExpense = foodExpense;
	}
	
	public long getTravelExpense() {
		return travelExpense;
	}
	public void setTravelExpense(long travelExpense) {
		this.travelExpense = travelExpense;
	}
	public long getOtherExpenss() {
		return otherExpenss;
	}
	public void setOtherExpenss(long otherExpenss) {
		this.otherExpenss = otherExpenss;
	}
	public String getFoodBill() {
		return foodBill;
	}
	public void setFoodBill(String foodBill) {
		this.foodBill = foodBill;
	}
	public String getTravelBill() {
		return travelBill;
	}
	public void setTravelBill(String travelBill) {
		this.travelBill = travelBill;
	}
	public String getOtherBill() {
		return otherBill;
	}
	public void setOtherBill(String otherBill) {
		this.otherBill = otherBill;
	}
	public long getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	public Date getDateOfReimburse() {
		return dateOfReimburse;
	}
	public void setDateOfReimburse(Date dateOfReimburse) {
		this.dateOfReimburse = dateOfReimburse;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public long getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(long totalExpense) {
		this.totalExpense = totalExpense;
	}
	
	public ReimbursementModel(long reimburceId, long employeeId, long foodExpense, long travelExpense,
			long otherExpenss, String foodBill, String travelBill, String otherBill, long supervisorId,
			Date dateOfReimburse, Date createdDate, long totalExpense) {
		super();
		this.reimburceId = reimburceId;
		this.employeeId = employeeId;
		this.foodExpense = foodExpense;
		this.travelExpense = travelExpense;
		this.otherExpenss = otherExpenss;
		this.foodBill = foodBill;
		this.travelBill = travelBill;
		this.otherBill = otherBill;
		this.supervisorId = supervisorId;
		this.dateOfReimburse = dateOfReimburse;
		this.createdDate = createdDate;
		this.totalExpense = totalExpense;
	}
	public ReimbursementModel() {
		super();
	}
	
	
	
	
}
