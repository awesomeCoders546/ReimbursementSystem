package com.sjp;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.sjp.Model.AttendanceModel;

import com.sjp.service.AttendanceService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class AttendanceServiceTest {

	@Autowired
	private AttendanceService attendanceService ; 


	@Test
	void testDeleteAttendence() {
	//	AttendanceModel attendance=new AttendanceModel();
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


}
