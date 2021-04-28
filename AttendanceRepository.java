package com.sjp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sjp.Model.AttendanceModel;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel, Integer>
{

	List<AttendanceModel> findByEmployeeId(int employeeId);
	
	@Query(value="select * from attendance_model where employee_id=? and created_date=(select max(created_date) from attendance_model)" , nativeQuery=true)
	AttendanceModel findLogoutTime(int employeeId);

}
