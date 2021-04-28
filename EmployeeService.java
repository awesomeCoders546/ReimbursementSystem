package com.sjp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Exception.ResourceNotFound;
import com.sjp.Model.AttendanceModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Repository.AttendanceRepository;
import com.sjp.Repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private AttendanceRepository attendRepo;
	
	public EmployeeModel updateEmployee(EmployeeModel employee)
	{
		Optional<EmployeeModel> empployeeOptional=empRepo.findById(employee.getEmployeeId());
		EmployeeModel emp=empployeeOptional.get();
		emp.setMobile(employee.getMobile());
		emp.setEmail(employee.getEmail());
		emp.setPassword(employee.getPassword());
		empRepo.save(emp);
		return emp;
	}
	
	public void deleteEmployee(long id)
	{
		empRepo.deleteById(id);
		
	}
	public EmployeeModel employeeLogin(EmployeeModel employee)
	{
		AttendanceModel attendModel=new AttendanceModel();
		EmployeeModel model=empRepo.findByEmailAndPassword(employee.getEmail(),employee.getPassword());
		if(model!=null)
		{
			attendModel.setStatus("Present");
			attendModel.setEmployeeId(model.getEmployeeId());
			attendModel.setEmployeeName(model.getName());
			attendRepo.save(attendModel);
			return model;
		}
		else
		{
			throw new ResourceNotFound("invalid credentials");
		}
	}
	public EmployeeModel getEmployee(long id)
    {
    	Optional<EmployeeModel> emp=empRepo.findById(id);
    	EmployeeModel employee=emp.get();
    	return employee; 
    	
    }
	public List<EmployeeModel> getEmployee() {
		List<EmployeeModel> emp=empRepo.findAll();
		//EmployeeModel employee=emp.get();
		return emp;
	}
	

}
