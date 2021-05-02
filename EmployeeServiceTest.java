package com.sjp;



import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sjp.Model.EmployeeModel;
import com.sjp.service.EmployeeService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Test
	void testUpdateEmployee() {
		EmployeeModel employee1=new EmployeeModel();
		employee1.getEmployeeId();
		employee1.setPassword("lak@1234");
		employee1.setMobile("9090909090");
		
		
	}

	@Test
	void testDeleteEmployee() {
		
		employeeService.deleteEmployee(1);
	}

	@Test
	void testEmployeeLogin() {
		EmployeeModel employee1=new EmployeeModel();
		employee1.setEmail("tyagi@gmail.com");
		employee1.setPassword("lak@123");
		Assert.assertEquals("tyagi@gmail.com", employee1.getEmail());
		Assert.assertEquals("lak@123", employee1.getPassword());
		employeeService.employeeLogin(employee1);
	}

	@Test
	void testGetEmployeeLong() {
		employeeService.getEmployee(1);
	}

	@Test
	void testGetEmployee() {
		employeeService.getEmployee();
	}

}
