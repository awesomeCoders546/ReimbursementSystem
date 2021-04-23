package com.sjp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Model.AccountantModel;
import com.sjp.Repository.AccountantRepo;

@Service
public class AccountantService
{
	@Autowired
	private AccountantRepo accRepo;
	
	public AccountantModel addAccountant(AccountantModel accountant)
	{
		return accRepo.save(accountant);
	}
	public AccountantModel accountantLogin(AccountantModel accountant)
	{
		return accRepo.findByEmailAndPassword(accountant.getEmail(),accountant.getPassword());
	}
	

}
