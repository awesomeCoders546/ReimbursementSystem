package com.sjp;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sjp.Model.AccountantModel;
import com.sjp.service.AccountantService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class AccountantServiceTest {

	@Autowired
	private AccountantService accountantService;
	
	@Test
	void testAddAccountant() {
		AccountantModel accountantModel=new AccountantModel();
		accountantModel.setAccountantId(1);
		accountantModel.setEmail("manujchauhan2040@gmail.com");	
		accountantModel.setMobile("9999616441");
		accountantModel.setName("manuj");
		accountantModel.setPassword("man@123");
		accountantService.addAccountant(accountantModel);
	}
	

	@Test
	void testAccountantLogin() {
		AccountantModel accountantModel=new AccountantModel();
		accountantModel.setName("manuj");
		accountantModel.setPassword("man@123");
		//Assert.assertEquals(accountantModel.setName("manuj"), accountantModel.getName());
		accountantService.accountantLogin(accountantModel);
	}

}