package com.sjp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjp.Model.DepartmentModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Model.ReimbursementModel;
import com.sjp.Model.SupervisorModel;
import com.sjp.service.DepartmentService;
import com.sjp.service.EmployeeService;
import com.sjp.service.ReimburseService;
import com.sjp.service.SuperVisorService;
@RestController
@RequestMapping("/reimbursementapi/all")
public class ReimbursementController
{
	@Autowired
	private DepartmentService departService;
	@Autowired
	private SuperVisorService superService;
	@Autowired
	private EmployeeService empService;
	@Autowired
	private ReimburseService reimburseService;
 	
	@PostMapping("/saveDepartment")
	public DepartmentModel saveDepartment(@RequestBody DepartmentModel department)
	{
		return departService.saveDepartment(department);
	}
	
	@GetMapping("/getAllDepart")
	public List<DepartmentModel> getAllDepart()
	{
		return departService.getallDepartment();
	}
	
	@GetMapping("/getById/{id}")
	public DepartmentModel getById(@PathVariable long id)
	{
		return departService.getDepartById(id);
	}
	
	@PutMapping("/updateDepart")
	public DepartmentModel updateDepartment(@RequestBody DepartmentModel department)
	{
		return departService.updateDepartmentModel(department);
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public void deleteDepartment(@PathVariable long id )
	{
		departService.deleteDepartment(id);
	}
	@PostMapping("/addSupervisor")
	public SupervisorModel addSupervisor(@RequestBody SupervisorModel supervisor)
	{
		return superService.addSupervisor(supervisor);
	}
	@PutMapping("/addEmployeesToSupervisor")
	public SupervisorModel addEmployeeTosuperVisor(@RequestBody SupervisorModel superModel)
	{
		return superService.addEmployeeToSuperVisor(superModel);
	}
	@PostMapping("/supervisorLogin")
	public SupervisorModel supervisorLogin(@RequestBody SupervisorModel superModel)
	{
		return superService.loginSuperVisor(superModel);
	}
	@GetMapping("/getAllSuperVisor")
	public List<SupervisorModel> getAllSuperVisor()
	{
		return superService.getAllSupervisor();
	}
	
	@GetMapping("/getsupervisorById/{id}")
	public Optional<SupervisorModel> getsupervisorById(@PathVariable long id)
	{
		return superService.getById(id);
	}
	@DeleteMapping("/deleteSupervisor/{id}")
	public void deleteSuperVisorById(@PathVariable long id)
	{
	       superService.deleteSupervisor(id);
	}
	@PutMapping("/updateEmployee")
	public EmployeeModel updateEmployee(@RequestBody EmployeeModel emp)
	{
		return empService.updateEmployee(emp);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		 empService.deleteEmployee(id);
	}
	
	@PostMapping("/employeeLogin")
	public EmployeeModel employeeLogin(@RequestBody EmployeeModel emp)
	{ 
		return empService.employeeLogin(emp);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public EmployeeModel viewEmployee(@PathVariable long id)
	{
		return empService.getEmployee(id);
	}
	@GetMapping("/getAllEmployee")
	public List<EmployeeModel> GetEmployee()
	{
		return empService.getEmployee();
	}
	@PostMapping("/requestReimburse")
	public ReimbursementModel requestReimburse(@RequestBody ReimbursementModel reimburse)
	{
		return reimburseService.requestReimburse(reimburse);
	}
	
}
