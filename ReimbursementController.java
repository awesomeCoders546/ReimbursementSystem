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
 	
	
	//aading department
	@PostMapping("/saveDepartment")
	public DepartmentModel saveDepartment(@RequestBody DepartmentModel department)
	{
		return departService.saveDepartment(department);
	}
	
	//getAllDeparrment
	@GetMapping("/getAllDepart")
	public List<DepartmentModel> getAllDepart()
	{
		return departService.getallDepartment();
	}
	
	//getting department on the basis of id
	@GetMapping("/getById/{id}")
	public DepartmentModel getById(@PathVariable long id)
	{
		return departService.getDepartById(id);
	}
	
	//updating department
	@PutMapping("/updateDepart")
	public DepartmentModel updateDepartment(@RequestBody DepartmentModel department)
	{
		return departService.updateDepartmentModel(department);
	}
	
	//deleteDepartment
	@DeleteMapping("/deleteDepartment/{id}")
	public void deleteDepartment(@PathVariable long id )
	{
		departService.deleteDepartment(id);
	}
	//adding supervisor to department
	@PostMapping("/addSupervisor")
	public SupervisorModel addSupervisor(@RequestBody SupervisorModel supervisor)
	{
		return superService.addSupervisor(supervisor);
	}
	//adding empployee under supervisor
	@PutMapping("/addEmployeesToSupervisor")
	public SupervisorModel addEmployeeTosuperVisor(@RequestBody SupervisorModel superModel)
	{
		return superService.addEmployeeToSuperVisor(superModel);
	}
	
	//logging for supervisor
	@PostMapping("/supervisorLogin")
	public SupervisorModel supervisorLogin(@RequestBody SupervisorModel superModel)
	{
		return superService.loginSuperVisor(superModel);
	}
	
	//get all the supervisor
	@GetMapping("/getAllSuperVisor")
	public List<SupervisorModel> getAllSuperVisor()
	{
		return superService.getAllSupervisor();
	}
	
	//get all the supervisor on the basis of id
	@GetMapping("/getsupervisorById/{id}")
	public Optional<SupervisorModel> getsupervisorById(@PathVariable long id)
	{
		return superService.getById(id);
	}
	
	//deleteSuperviso
	@DeleteMapping("/deleteSupervisor/{id}")
	public void deleteSuperVisorById(@PathVariable long id)
	{
	       superService.deleteSupervisor(id);
	}
	//updateEmployee
	@PutMapping("/updateEmployee")
	public EmployeeModel updateEmployee(@RequestBody EmployeeModel emp)
	{
		return empService.updateEmployee(emp);
	}
	
	//deleting employee
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		 empService.deleteEmployee(id);
	}
	//employeeLogin
	@PostMapping("/employeeLogin")
	public EmployeeModel employeeLogin(@RequestBody EmployeeModel emp)
	{ 
		return empService.employeeLogin(emp);
	}
	//getEmployeeById
	@GetMapping("/getEmployeeById/{id}")
	public EmployeeModel viewEmployee(@PathVariable long id)
	{
		return empService.getEmployee(id);
	}
	///getAllEmployee
	@GetMapping("/getAllEmployee")
	public List<EmployeeModel> GetEmployee()
	{
		return empService.getEmployee();
	}
	
	//adding reimbursement
	@PostMapping("/requestReimburse")
	public ReimbursementModel requestReimburse(@RequestBody ReimbursementModel reimburse)
	{
		return reimburseService.requestReimburse(reimburse);
	}
	//api from where supervisor can approved or delete reimbursement request
	@PutMapping("/supervisorStatus")
	public ReimbursementModel updateSuperStatus(@RequestBody ReimbursementModel reimburse)
	{
		return reimburseService.updateSupervisorStatus(reimburse);
	}
	
	///api from where accountant can approved or delete reimbursement request
	@PutMapping("/accountantStatus")
	public ReimbursementModel updateAccountantStatus(@RequestBody ReimbursementModel reimburse)
	{
		return reimburseService.updateAccountantStatus(reimburse);
	}
	//not in use
	@GetMapping("/totaltravelexpense")
	public long getTotalTravelExpense()
	{
		return reimburseService.getTravelExpenses();
	}
	
	//displaying all reimburse to admin
	@GetMapping("/totalExpense")
	public List<ReimbursementModel> getTotalExpense()
	{
		return reimburseService.allExpense();
	}
	
	//displaying all reimbursement to the supervisor
	@GetMapping("/totalReimburse/{id}")
	public List<ReimbursementModel> getsuperReimburse(@PathVariable int id)
	{
		return reimburseService.findBySuperId(id);
	}
	//displaying all the reimbursement to accountant
	@GetMapping("/accountReimburse")
	public List<ReimbursementModel> getAccountanntReimburse()
	{
		return reimburseService.findAccountant();
	}
	//displaying reimbursement to employee
	@GetMapping("/employeeReimburse/{id}")
	public List<ReimbursementModel> getEmployeeReimburse(@PathVariable long id)
	{
		return reimburseService.getEmployeeReimburse(id);
	}
	//deleting reimbursement on the basis of reimbursement id
	@DeleteMapping("/removeReimburse/{id}")
	public List<ReimbursementModel> removeReimburse(@PathVariable long id)
	{
		return reimburseService.getEmployeeReimburse(id);
	}
}
