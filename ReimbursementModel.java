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
import javax.persistence.Transient;

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
	@Column(name="supervisor_status")
	private String supervisorStatus;
	@Column(name="accontant_status")
	private String accontantStatus;
	public long getTotalTravelExpenses() {
		return totalTravelExpenses;
	}
	public void setTotalTravelExpenses(long totalTravelExpenses) {
		this.totalTravelExpenses = totalTravelExpenses;
	}
	public long getTotalFoodExpenses() {
		return totalFoodExpenses;
	}
	public void setTotalFoodExpenses(long totalFoodExpenses) {
		this.totalFoodExpenses = totalFoodExpenses;
	}
	public long getTotalOtherExpenses() {
		return totalOtherExpenses;
	}
	public void setTotalOtherExpenses(long totalOtherExpenses) {
		this.totalOtherExpenses = totalOtherExpenses;
	}
	@Column(name="supervisor_id")
	private long supervisorId;
	@Column(name="date_of_Reimburse")
	private Date dateOfReimburse;
	@Column(name = "created_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name="total_expense")
	private long totalExpense;
	@Transient
	private long totalTravelExpenses;
	@Transient
	private long totalFoodExpenses;
	@Transient
	private long totalOtherExpenses;
	
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
	public String getSupervisorStatus() {
		return supervisorStatus;
	}
	public void setSupervisorStatus(String supervisorStatus) {
		this.supervisorStatus = supervisorStatus;
	}
	public String getAccontantStatus() {
		return accontantStatus;
	}
	public void setAccontantStatus(String accontantStatus) {
		this.accontantStatus = accontantStatus;
	}
	public ReimbursementModel(long reimburceId, long employeeId, long foodExpense, long travelExpense,
			long otherExpenss, String foodBill, String travelBill, String otherBill, String supervisorStatus,
			String accontantStatus, long supervisorId, Date dateOfReimburse, Date createdDate, long totalExpense,
			long totalTravelExpenses, long totalFoodExpenses, long totalOtherExpenses) {
		super();
		this.reimburceId = reimburceId;
		this.employeeId = employeeId;
		this.foodExpense = foodExpense;
		this.travelExpense = travelExpense;
		this.otherExpenss = otherExpenss;
		this.foodBill = foodBill;
		this.travelBill = travelBill;
		this.otherBill = otherBill;
		this.supervisorStatus = supervisorStatus;
		this.accontantStatus = accontantStatus;
		this.supervisorId = supervisorId;
		this.dateOfReimburse = dateOfReimburse;
		this.createdDate = createdDate;
		this.totalExpense = totalExpense;
		this.totalTravelExpenses = totalTravelExpenses;
		this.totalFoodExpenses = totalFoodExpenses;
		this.totalOtherExpenses = totalOtherExpenses;
	}
	public ReimbursementModel() {
		super();
	}
	
	
	
	
	
	
}
