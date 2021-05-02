package com.sjp;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sjp.Model.DepartmentModel;
import com.sjp.service.DepartmentService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class DepartmentServiceTest {
	@Autowired
	private DepartmentService departmentService;

	@Test
	void testSaveDepartment() {
		DepartmentModel departmentModel=new DepartmentModel();
		departmentModel.setDepartmentId(1);
		departmentModel.setDepartmentName("Information Technology");
		departmentService.saveDepartment(departmentModel);
	}

	@Test
	void testGetallDepartment() {
		departmentService.getallDepartment();
	}
	
	@Test
	void testGetDepartById() {
		departmentService.getDepartById(2);
	}

	@Test
	void testUpdateDepartmentModel() {
		DepartmentModel departmentModel=new DepartmentModel();
		departmentModel.setDepartmentId(2);
		departmentModel.setDepartmentName("Information");
		departmentService.updateDepartmentModel(departmentModel);
	}
	
	@Test
	void testDeleteDepartment() {
		departmentService.deleteDepartment(2);
	}

}