package com.sjp.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.sjp.Model.TimeSheetModel;
@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheetModel, Integer> {
	
	List<TimeSheetModel> findByEmployeeId(long id);

	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='waiting' and supervisor_id=?;" ,nativeQuery=true)
	List<TimeSheetModel> findAllWaiting(long superVisorId);
	
	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='approved' and accountant_approved='waiting';" ,nativeQuery=true)
	List<TimeSheetModel> findAccountantApproval();
	
	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='approved' and supervisor_id=?;" ,nativeQuery=true)
	List<TimeSheetModel> findAllSuperVisorApproved(long supervisorId);
	
	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='disapproved' and supervisor_id=?;" ,nativeQuery=true)
	List<TimeSheetModel> findAllSuperVisorDisApproved(long supervisorId);
	
	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='approved' and accountant_approved='approved';" ,nativeQuery=true)
	List<TimeSheetModel> findAllAccountantApproved();
	
	@Query(value="select * from reimbursement.time_sheet where supervisor_approved='approved' and accountant_approved='disapproved';" ,nativeQuery=true)
	List<TimeSheetModel> findAllAccountantDisApproved();

	@Query(value="select * from reimbursement.time_sheet where project_name='timeSheet' and task='codding';" ,nativeQuery=true)
	List<TimeSheetModel> findAllCodding();
	
	@Query(value="select * from reimbursement.time_sheet where project_name='timeSheet' and task='requirementGathering';" ,nativeQuery=true)
	List<TimeSheetModel> findAllRequirementGathering();
	
	@Query(value="select * from reimbursement.time_sheet where project_name='timeSheet' and task='testing';" ,nativeQuery=true)
	List<TimeSheetModel> findAllTesting();

	@Query(value="select * from reimbursement.time_sheet where project_name='timeSheet' and task='meeting';" ,nativeQuery=true)
	List<TimeSheetModel> findAllMeeting();
	
	@Query(value="SELECT *FROM reimbursement.time_sheet WHERE YEARWEEK(date) = YEARWEEK(NOW());" ,nativeQuery=true)
	List<TimeSheetModel> findByWeek();	
	
	@Query(value="SELECT * FROM reimbursement.time_sheet WHERE MONTH(date) = ? AND YEAR(date) = ?",nativeQuery=true)
	List<TimeSheetModel> findByMonth(String month, String year);
	
	@Query(value="SELECT *FROM reimbursement.time_sheet WHERE employee_id=? and YEARWEEK(date) = YEARWEEK(NOW());" ,nativeQuery=true)
	List<TimeSheetModel> findByWeekAndEmployeId(long id);
	
	@Query(value="SELECT * FROM reimbursement.time_sheet WHERE employee_id=? and  MONTH(date) = ? AND YEAR(date) = ?",nativeQuery=true)
	List<TimeSheetModel> findByMonthAndEmployeeId(long id,String month, String year);
	
	@Query(value="SELECT * FROM reimbursement.time_sheet WHERE cast(date as date) = ?",nativeQuery=true)
	List<TimeSheetModel> findByDay(String timeDate);
	
	@Query(value="SELECT * FROM reimbursement.time_sheet WHERE cast(date as date) = ? and employee_id=?",nativeQuery=true)
	List<TimeSheetModel> findByDayAndEmployeeId(String timeDate,long employeeId);

	List<TimeSheetModel> findByTask(String task);

	List<TimeSheetModel> findByTaskAndEmployeeId(String task,long employeeId);

	List<TimeSheetModel> findByProjectName(String projectName);

	List<TimeSheetModel> findByProjectNameAndEmployeeId(String projectName, long employeeId);
  
	@Query(value="SELECT * FROM  reimbursement.time_sheet WHERE date BETWEEN ? and ? and employee_id=? and supervisor_approved='approved' and accountant_approved='waiting';",nativeQuery=true)
	List<TimeSheetModel> findByWeeklyAndEmployeeId(String startDate,String endDate,long employeeId);
	
	@Query(value="SELECT * FROM  reimbursement.time_sheet WHERE date BETWEEN ? and ? ;",nativeQuery=true)
	List<TimeSheetModel> findByWeekly(String startDate,String endDate);

	@Query(value="SELECT *FROM reimbursement.time_sheet WHERE YEARWEEK(date) = YEARWEEK(NOW()) and supervisor_id=?;" ,nativeQuery=true)
	List<TimeSheetModel> findBySuperWeek(long superId);	
	
	@Query(value="SELECT *FROM reimbursement.time_sheet WHERE YEARWEEK(date) = YEARWEEK(NOW()) and supervisor_id=? and employee_id=?;" ,nativeQuery=true)
	List<TimeSheetModel> findBySuperWeekAndEmployee(long superId,long employeeId);	
	
	@Query(value="SELECT * FROM  reimbursement.time_sheet WHERE date BETWEEN ? and ? and supervisor_id=?;",nativeQuery=true)
	List<TimeSheetModel> findBySuperWeekly(String startDate,String endDate,long superId);
	
	@Query(value="SELECT * FROM  reimbursement.time_sheet WHERE date BETWEEN ? and ? and supervisor_approved='waiting' and supervisor_id=? and employee_id=?;",nativeQuery=true)
	List<TimeSheetModel> findBySuperWeeklyAndEmployee(String startDate,String endDate,long superId,long employeeId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE time_sheet SET supervisor_approved = ? WHERE time_sheet_id=?;",nativeQuery=true)
	void updateSuperStatus(String approved,int timeSheetId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE time_sheet SET accountant_approved = ? WHERE time_sheet_id=?;",nativeQuery=true)
	void updateAccountantStatus(String approved,int timeSheetId);
	
}
