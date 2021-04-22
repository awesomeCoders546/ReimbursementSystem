package com.sjp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Exception.ResourceNotFound;
import com.sjp.Model.DepartmentModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Model.SupervisorModel;
import com.sjp.Repository.EmployeeRepository;
import com.sjp.Repository.SupervisorRepo;

@Service
public class SuperVisorService
{
	@Autowired
	private SupervisorRepo superRepo;
	@Autowired
	private EmployeeRepository empRepository;
	
	public SupervisorModel addSupervisor(SupervisorModel supervisor)
	{
		
		boolean exits=superRepo.existsByEmail(supervisor.getEmail());
		if(exits==false)
		{
			boolean flag=true;
		 List<EmployeeModel> employees=supervisor.getEmployees();
		 if(employees!=null)
		 {
		 for(EmployeeModel emp:employees)
		{
			flag=empRepository.existsEmployeeModelByEmail(emp.getEmail());
	    }
		if(flag==false)
		{
			
			supervisor.setTotalEmployeesUnder(employees.size());
			return superRepo.save(supervisor);
		}
		else if (employees.isEmpty()) {
			System.out.println("myname is hello");
			supervisor.setTotalEmployeesUnder(0);
			return superRepo.save(supervisor);
		}
		}
		 else
		 {
			 supervisor.setTotalEmployeesUnder(0);
				return superRepo.save(supervisor);
		 }
		}
		
		else
		{
			throw new ResourceNotFound("email already exist");
		}
		return supervisor;
	}
	        public SupervisorModel addEmployeeToSuperVisor(SupervisorModel supervisorModel)
	        {
	        	boolean flag=true;
	        	
	        	Optional<SupervisorModel> supervisor=superRepo.findById(supervisorModel.getSupervisorId());
	        	SupervisorModel superModel=supervisor.get();
	        	List<EmployeeModel> employees=new ArrayList<EmployeeModel>();
	        	for(EmployeeModel emp:supervisorModel.getEmployees())
	        	{
	        		
	        		flag=empRepository.existsEmployeeModelByEmail(emp.getEmail());
	        		emp.setName(emp.getName());
	        		emp.setEmail(emp.getEmail());
	        		emp.setMobile(emp.getMobile());
	        		emp.setPassword(emp.getPassword());
	        		emp.setSalary(emp.getSalary());
	        		emp.setDepartmentId(emp.getDepartmentId());
	        		employees.add(emp);
	        	}
	        	superModel.setEmployees(employees);
	        	superModel.setTotalEmployeesUnder(superModel.getTotalEmployeesUnder()+supervisorModel.getEmployees().size());
	        	if(flag==false)
	        	{
	        		System.out.println(flag);
	        	return superRepo.save(superModel);
	        	}
	        	else
	        	{
	        		return superModel;
	        	}
	        }
	        public SupervisorModel loginSuperVisor(SupervisorModel superModel)
	        {
	        	SupervisorModel supervisor=superRepo.findByEmailAndPassword(superModel.getEmail(),superModel.getPassword());
	        	if(supervisor!=null)
	        	{
	        	return supervisor;
	        	}
	        	else
	        	{
	        		throw new ResourceNotFound("Invalid credentials");
	        	}
	        }
	        
	        public List<SupervisorModel> getAllSupervisor()
	        {
	        	return superRepo.findAll();
	        	
	        }
	        
	        public Optional<SupervisorModel> getById(long id)
	        {
	        	return superRepo.findById(id);
	        }
	        
	        public void deleteSupervisor(long id)
	        {
	        	 superRepo.deleteById(id);
	        }

}
