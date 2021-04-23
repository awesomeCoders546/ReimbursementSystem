package com.sjp.service;



import java.util.List;
import java.util.Optional;

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
		reimburse.setSupervisorStatus("waiting");
		reimburse.setAccontantStatus("waiting");
	     return reimburseRepo.save(reimburse);
	}
	
	public ReimbursementModel updateSupervisorStatus(ReimbursementModel reimburse)
	{
		Optional<ReimbursementModel> reimburseM=reimburseRepo.findById(reimburse.getReimburceId());
		ReimbursementModel model=reimburseM.get();
		model.setSupervisorStatus(reimburse.getSupervisorStatus());
		return reimburseRepo.save(model);		
	}
	
	public ReimbursementModel updateAccountantStatus(ReimbursementModel reimburse)
	{
		Optional<ReimbursementModel> reimburseM=reimburseRepo.findById(reimburse.getReimburceId());
		ReimbursementModel model=reimburseM.get();
		model.setAccontantStatus(reimburse.getAccontantStatus());
		return reimburseRepo.save(model);		
	}
	
	public long getTravelExpenses()
	{
		ReimbursementModel reimburse=new ReimbursementModel();
		long expense=reimburseRepo.findTravelExpenses();
		reimburse.setTotalTravelExpenses(expense);
		return reimburse.getTotalTravelExpenses();
	}
	public List<ReimbursementModel> allExpense()
	{
		long totalTralvel=0;
		long totalFood=0;
		long otherTotal=0;
		ReimbursementModel reimburseModel=new ReimbursementModel();
		List<ReimbursementModel> allExpenses =reimburseRepo.findAll();
		for(ReimbursementModel reimburse:allExpenses)
		{
		  totalTralvel+=reimburse.getTravelExpense();
		  totalFood+=reimburse.getFoodExpense();
		   otherTotal+=reimburse.getOtherExpenss();
		 
			
		}
		reimburseModel.setTotalTravelExpenses(totalTralvel);
		reimburseModel.setTotalFoodExpenses(totalFood);
		reimburseModel.setTotalOtherExpenses(otherTotal);
		allExpenses.add(reimburseModel);
		return allExpenses;
	}
	public List<ReimbursementModel> findBySuperId(int superVisorId)
	{
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllWaiting(superVisorId);
		return allReimburse;
	}
	public List<ReimbursementModel> findAccountant()
	{
		List<ReimbursementModel> allReimburse=reimburseRepo.findAccountantApproval();
		return allReimburse;
	}
	public List<ReimbursementModel> getEmployeeReimburse(long id)
	{
		List<ReimbursementModel> allReimburse=reimburseRepo.findByEmployeeId(id);
		return allReimburse;
	}
	public void deleteReimburse(long id)
	{
		reimburseRepo.deleteById(id);
	}

}
