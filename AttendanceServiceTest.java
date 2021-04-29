package com.sjp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.sjp.Model.AttendanceModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Repository.EmployeeRepository;
import com.sjp.service.AttendanceService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class AttendanceServiceTest {

	@Autowired
	private AttendanceService attendanceService ; 
	@Autowired
	private EmployeeRepository employeeRepo;
/*
	@Test
	void testSaveAttendence() {
		AttendanceModel attendance=new AttendanceModel();
		EmployeeModel employeeModel=new EmployeeModel();
		employeeModel.setEmployeeId(21);
		employeeModel.setName("Rohit");
		employeeModel.setMobile("9999000999");
		employeeModel.setEmail("rohit@gmail.com");
		employeeModel.setSalary(23000);
		employeeModel.setPassword("rohit@123");
		Optional<EmployeeModel> employee=employeeRepo.findById(attendance.getEmployeeId());
		EmployeeModel emp=employee.get();
		attendance.setEmployeeName(emp.getName());
		
		attendanceService.saveAttendence(attendance);
	}
		*/
	

	@Test
	void testDeleteAttendence() {
		AttendanceModel attendance=new AttendanceModel();
		attendanceService.deleteAttendence(1);
	}

	@Test
	void testGetAttendence() {
		attendanceService.getAttendence(1);
	}

	@Test
	void testGetAll() {
		attendanceService.getAll();
	}

	@Test
	void testUpdate() {
		AttendanceModel attendance=new AttendanceModel();
		attendance.setAttendenceId(1);
		attendance.setEmployeeId(22);
		attendance.setEmployeeName("Rohit");
		attendance.setLogHours(9);
		attendance.setStatus("present");
		attendanceService.update(attendance);
	}
//
//	@Test
//	void testGetEmployeeAttendance() {
//		attendanceService.getEmployeeAttendance(1);
//	}

//	@Test
//	void testUpdateAttendance() {
//		attendanceService.updateAttendance(1);
//	}

}
