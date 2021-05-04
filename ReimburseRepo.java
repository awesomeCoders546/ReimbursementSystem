package com.sjp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sjp.Model.ReimbursementModel;

@Repository
public interface ReimburseRepo extends JpaRepository<ReimbursementModel, Long>
{

	@Query(value="SELECT SUM(travel_expense) tarvel_expense FROM reimbursement_table",nativeQuery=true)
	long findTravelExpenses();
	
	@Query(value="SELECT SUM(food_expense) tarvel_expense FROM reimbursement_table",nativeQuery=true)
	long findFoodExpenses();
	
	@Query(value="SELECT SUM(other_expense) tarvel_expense FROM reimbursement_table",nativeQuery=true)
	long findOtherExpenses();

	@Query(value="select * from reimbursement_table where supervisor_status='waiting' and supervisor_id=?;" ,nativeQuery=true)
	List<ReimbursementModel> findAllWaiting(int superVisorId);

	@Query(value="select * from reimbursement_table where supervisor_status='approved' and accontant_status='waiting';" ,nativeQuery=true)
	List<ReimbursementModel> findAccountantApproval();

	List<ReimbursementModel> findByEmployeeId(long id);
	
	@Query(value="select * from reimbursement_table where supervisor_status='approved' and supervisor_id=?;" ,nativeQuery=true)
	List<ReimbursementModel> findAllSuperVisorApproved(int supervisorId);
	
	@Query(value="select * from reimbursement_table where supervisor_status='disapproved' and supervisor_id=?;" ,nativeQuery=true)
	List<ReimbursementModel> findAllSuperVisorDisApproved(int supervisorId);

	@Query(value="select * from reimbursement_table where supervisor_status='approved' and accontant_status='approved';" ,nativeQuery=true)
	List<ReimbursementModel> findAllAccountantApproved();
	
	@Query(value="select * from reimbursement_table where supervisor_status='approved' and accontant_status='disapproved';" ,nativeQuery=true)
	List<ReimbursementModel> findAllAccountantDisApproved();

}
