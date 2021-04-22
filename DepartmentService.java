package com.sjp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Model.DepartmentModel;
import com.sjp.Repository.DepartmentRepo;

@Service
public class DepartmentService 
{
	@Autowired
	private DepartmentRepo departRepo;
	
	public DepartmentModel saveDepartment(DepartmentModel depart)
	{
		return departRepo.save(depart);
	}
	
	public List<DepartmentModel> getallDepartment()
	{
		
		return departRepo.findAll();
	}
	
	public DepartmentModel getDepartById(long id)
	{
	       Optional<DepartmentModel> department= departRepo.findById(id);
	       return department.get();
	}
	public DepartmentModel updateDepartmentModel(DepartmentModel depart)
	{
		Optional<DepartmentModel> department= departRepo.findById(depart.getDepartmentId());
		DepartmentModel departments=department.get();
		return departRepo.save(departments);
	}
	public void deleteDepartment(long id)
	{
		departRepo.deleteById(id);
	}

}
