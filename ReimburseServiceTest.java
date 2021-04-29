package com.sjp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sjp.Model.ReimbursementModel;
import com.sjp.service.ReimburseService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class ReimburseServiceTest {

	@Autowired
	private ReimburseService reimburseService;
	@Test
	void testRequestReimburse() {
		ReimbursementModel reimburse=new ReimbursementModel();
		reimburse.setAccontantStatus("waiting");
		reimburse.setSupervisorStatus("waiting");
		reimburseService.requestReimburse(reimburse);
		
	}


	@Test
	void testGetTravelExpenses() {
		reimburseService.getTravelExpenses();
	}

	@Test
	void testAllExpense() {
		reimburseService.allExpense();
	}

	@Test
	void testFindBySuperId() {
		reimburseService.findBySuperId(1);
	}

	@Test
	void testFindAccountant() {
		reimburseService.findAccountant();
	}

	@Test
	void testGetEmployeeReimburse() {
		reimburseService.getEmployeeReimburse(1);
	}

	@Test
	void testDeleteReimburse() {
		reimburseService.deleteReimburse(1);
	}

}
