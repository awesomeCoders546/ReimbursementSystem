package com.sjp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sjp.Model.ReimbursementModel;

import com.sjp.Repository.ReimburseRepo;


@Service
public class ReimburseService 
{
	@Autowired
	private ReimburseRepo reimburseRepo;
	
	public ReimbursementModel requestReimburse(ReimbursementModel reimburse)
	{
		
		reimburse.setTotalExpense(reimburse.getFoodExpense()+reimburse.getTravelExpense()+reimburse.getOtherExpenss());
	     return reimburseRepo.save(reimburse);
	    
				
	}

}
