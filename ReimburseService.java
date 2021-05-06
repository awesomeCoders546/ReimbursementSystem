package com.sjp.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Exception.NoReimburseFound;
import com.sjp.Model.ReimbursementModel;

import com.sjp.Repository.ReimburseRepo;


@Service
public class ReimburseService 
{
	@Autowired
	private ReimburseRepo reimburseRepo;
	private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	public ReimbursementModel requestReimburse(ReimbursementModel reimburse)
	{
		logger.info("requestReimburse() of ReimbursementService is called!");
		reimburse.setTotalExpense(reimburse.getFoodExpense()+reimburse.getTravelExpense()+reimburse.getOtherExpenss());
		reimburse.setSupervisorStatus("waiting");
		reimburse.setAccontantStatus("waiting");
	     return reimburseRepo.save(reimburse);
	}
	
	public ReimbursementModel updateSupervisorStatus(ReimbursementModel reimburse)
	{
		logger.info("updateSupervisorStatus() of ReimbursementService is called!");
		Optional<ReimbursementModel> reimburseM=reimburseRepo.findById(reimburse.getReimburceId());
		ReimbursementModel model=reimburseM.get();
		model.setSupervisorStatus(reimburse.getSupervisorStatus());
		return reimburseRepo.save(model);		
	}
	
	public ReimbursementModel updateAccountantStatus(ReimbursementModel reimburse)
	{	
		logger.info("updateAccountantStatus() of ReimbursementService is called!");
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
		logger.info("allExpense() of ReimbursementService is called!");
		long totalTralvel=0;
		long totalFood=0;
		long otherTotal=0;
		ReimbursementModel reimburseModel=new ReimbursementModel();
		List<ReimbursementModel> allExpenses =reimburseRepo.findAll();
		List<ReimbursementModel> expense=new ArrayList<ReimbursementModel>();
		for(ReimbursementModel reimburse:allExpenses)
		{
		  totalTralvel+=reimburse.getTravelExpense();
		  totalFood+=reimburse.getFoodExpense();
		   otherTotal+=reimburse.getOtherExpenss();
		 
			
		}
		reimburseModel.setTotalTravelExpenses(totalTralvel);
		reimburseModel.setTotalFoodExpenses(totalFood);
		reimburseModel.setTotalOtherExpenses(otherTotal);
		expense.addAll(allExpenses);
		expense.add(reimburseModel);
		
		return expense;
	}
	public List<ReimbursementModel> findBySuperId(int superVisorId)
	{
		logger.info(" findBySuperId() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllWaiting(superVisorId);
		return allReimburse;
	}
	public List<ReimbursementModel> findAccountant()
	{
		logger.info("findAccountant() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAccountantApproval();
		return allReimburse;
	}
	public List<ReimbursementModel> getEmployeeReimburse(long id)
	{
		logger.info("getEmployeeReimburse() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findByEmployeeId(id);
		return allReimburse;
	}
	public void deleteReimburse(long id)
	{
		logger.info("deleteReimburse() of ReimbursementService is called!");
		reimburseRepo.deleteById(id);
	}
	public List<ReimbursementModel> aprrovedSupervisor(int superVisorId)
	{
	
		logger.info("aprrovedSupervisor() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllSuperVisorApproved(superVisorId);
		if(allReimburse.isEmpty())
		{
			logger.error("error in aprrovedSupervisor() of ReimbursementService");
			throw new NoReimburseFound("no aprroved reimbursement for Supervisor");
		}
		else
		{
			return allReimburse;
		}
	}
	
	public List<ReimbursementModel> disaprrovedSupervisor(int superVisorId)
	{
		
		logger.info("disaprrovedSupervisor() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllSuperVisorDisApproved(superVisorId);
		if(allReimburse.isEmpty())
		{
			logger.error("error in disaprrovedSupervisor() of ReimbursementService");
			throw new NoReimburseFound("no disaprroved reimbursement for Supervisor");
		}
		else
		{
			return allReimburse;
		}
	}
	
	public List<ReimbursementModel> allAccountantApproved()
	{
		logger.info("allAccountantApproved() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllAccountantApproved();
		if(allReimburse.isEmpty())
		{
			logger.error("error in allAccountantDisApproved() of ReimbursementService");
			throw new NoReimburseFound("no apprroved reimbursement for accountant");
		}
		else
		{
			return allReimburse;
		}
	}
	
	public List<ReimbursementModel> allAccountantDisApproved()
	{
		logger.info("allAccountantDisApproved() of ReimbursementService is called!");
		List<ReimbursementModel> allReimburse=reimburseRepo.findAllAccountantDisApproved();
		if(allReimburse.isEmpty())
		{
			logger.error("error in allAccountantDisApproved() of ReimbursementService");
			throw new NoReimburseFound("no disapproved reimbursement");
		}
		else
		{
			return allReimburse;
		}
		
	}
	
	public ReimbursementModel findReimburseById(long id)
	{
	   ReimbursementModel reimburse=new ReimbursementModel();
		
		try {
			Optional<ReimbursementModel> re= reimburseRepo.findById(id);
			 reimburse=re.get();
			logger.info("findReimburseById() of ReimbursementService is called!",id);
			if(reimburse!=null)
			{
				 return reimburse;
			}
			else
			{
			
				logger.error("no reimburse found");
				throw new NoReimburseFound("no reimbuurse for this id!");
			}
		}
		catch (Exception e) {
			logger.error("Error in findReimburseById() of reimburse service no reimburse found for this id");
			return reimburse;
		}
	  
	}
	public List<ReimbursementModel> findByMonth(String month,String year)
	{
		logger.info("findByMonth() of ReimbursementService is called!",month,year);
		long totalTralvel=0;
		long totalFood=0;
		long otherTotal=0;
		ReimbursementModel reimburseModel=new ReimbursementModel();
		List<ReimbursementModel> allExpenses= reimburseRepo.findByMonth(month,year);
		try
		{
			if(!allExpenses.isEmpty())
			{
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
			}
			else
			{
				throw new NoReimburseFound("Error! no reimburse for this month");
			}
			
		}
		catch (NoReimburseFound e) {
			logger.error("no reimburse for this month");
			return allExpenses;
		}
		return allExpenses;
				
	}

}
