package com.sjp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.sjp.Model.EmployeeModel;
import com.sjp.Model.SupervisorModel;
import com.sjp.service.SuperVisorService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class SuperVisorServiceTest {

	@Autowired
	private SuperVisorService superVisorService;
	

	@Test
	void testLoginSuperVisor() {
		SupervisorModel superVisor=new SupervisorModel();
		superVisor.setEmail("manu@gmail.com");
		superVisor.setPassword("man@123");
		Assert.assertEquals("manu@gmail.com", superVisor.getEmail());
		Assert.assertEquals("man@123", superVisor.getPassword());
		superVisorService.loginSuperVisor(superVisor);
	}

	@Test
	void testGetAllSupervisor() {
		superVisorService.getAllSupervisor();
	}

	@Test
	void testGetById() {
		superVisorService.getById(1);
	}

	@Test
	void testDeleteSupervisor() {
		superVisorService.deleteSupervisor(1);
	}

}