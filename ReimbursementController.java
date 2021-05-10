package com.sjp.controller;

import java.text.ParseException;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.sjp.Exception.ResourceNotFound;
import com.sjp.Model.AccountantModel;
import com.sjp.Model.AdminLogin;
import com.sjp.Model.AttendanceModel;
import com.sjp.Model.DepartmentModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Model.ReimbursementModel;
import com.sjp.Model.SupervisorModel;
import com.sjp.Repository.AdminLoginRepo;
import com.sjp.service.AccountantService;
import com.sjp.service.AttendanceService;
import com.sjp.service.DepartmentService;
import com.sjp.service.EmployeeService;
import com.sjp.service.ReimburseService;
import com.sjp.service.SuperVisorService;
@CrossOrigin
@RestController
public class ReimbursementController
{
	private static final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);
	@Autowired
	private DepartmentService departService;
	@Autowired
	private SuperVisorService superService;
	@Autowired
	private EmployeeService empService;
	@Autowired
	private ReimburseService reimburseService;
 	@Autowired
 	private AccountantService accountService;
 	@Autowired
 	private AttendanceService attendService;
	
 	@Autowired
	private AdminLoginRepo alr;
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody AdminLogin al) {
		AdminLogin adminlogin = new AdminLogin();
		String adminId = "admin@gmail.com";
		String adminP = "admin";
		if(alr.findAdminLoginByAdminId(adminId) == null) {
			adminlogin.setAdminId(adminId);
			adminlogin.setPassword(adminP);
			alr.save(adminlogin);
			System.out.println(al.getAdminId()+" "+al.getPassword());
			AdminLogin ad = alr.findAdminLoginByAdminId(al.getAdminId()); 
	        if(ad != null) {
	        	System.out.println("logedIn");
	        }else {
	        	throw new ResourceNotFound("Login failed");
	        }
		}else {
			System.out.println("1  "+al.getAdminId()+" "+al.getPassword());
			AdminLogin ad = alr.findAdminLoginByAdminId(al.getAdminId()); 
	        if(ad != null) {
	        	System.out.println("logedIn");
	        }else {
	        	throw new ResourceNotFound("Login failed");
	        }
		}
	}
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
		logger.info("addSupervisor() of reimbursementController is called!");
		return superService.addSupervisor(supervisor);
	}
	//adding empployee under supervisor
	@PutMapping("/addEmployeesToSupervisor")
	public SupervisorModel addEmployeeTosuperVisor(@RequestBody SupervisorModel superModel)
	{
		logger.info("addEmployeeTosuperVisor() of reimbursementController is called!");
		return superService.addEmployeeToSuperVisor(superModel);
	}
	
	//logging for supervisor
	@PostMapping("/supervisorLogin")
	public SupervisorModel supervisorLogin(@RequestBody SupervisorModel superModel)
	{
		logger.info("supervisorLogin() of reimbursementController is called!");
		return superService.loginSuperVisor(superModel);
	}
	
	//get all the supervisor
	@GetMapping("/getAllSuperVisor")
	public List<SupervisorModel> getAllSuperVisor()
	{
		logger.info("getAllSuperVisor() of reimbursementController is called!");
		return superService.getAllSupervisor();
	}
	
	//get all the supervisor on the basis of id
	@GetMapping("/getsupervisorById/{id}")
	public Optional<SupervisorModel> getsupervisorById(@PathVariable long id)
	{
		logger.info("getsupervisorById() of reimbursementController is called!");
		return superService.getById(id);
	}
	
	//deleteSuperviso
	@DeleteMapping("/deleteSupervisor/{id}")
	public void deleteSuperVisorById(@PathVariable long id)
	{
		logger.info("deleteSuperVisorById() of reimbursementController is called!");
	       superService.deleteSupervisor(id);
	}
	
	//deleting employee
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		logger.info("deleteEmployee() of reimbursementController is called!");
		 empService.deleteEmployee(id);
	}
	//employeeLogin
	@PostMapping("/employeeLogin")
	public EmployeeModel employeeLogin(@RequestBody EmployeeModel emp)
	{ 
		logger.info("employeeLogin() of reimbursementController is called!");
		return empService.employeeLogin(emp);
	}
	//getEmployeeById
	@GetMapping("/getEmployeeById/{id}")
	public EmployeeModel viewEmployee(@PathVariable long id)
	{
		logger.info("viewEmployee() of reimbursementController is called!");
		return empService.getEmployee(id);
	}
	///getAllEmployee
	@GetMapping("/getAllEmployee")
	public List<EmployeeModel> GetEmployee()
	{
		logger.info("GetEmployee() of reimbursementController is called!");
		return empService.getEmployee();
	}
	
	//adding reimbursement
	@PostMapping("/requestReimburse")
	public ReimbursementModel requestReimburse(@RequestBody ReimbursementModel reimburse)
	{
		logger.info("requestReimburse() of reimbursementController is called!");
		return reimburseService.requestReimburse(reimburse);
	}
	//api from where supervisor can approved or delete reimbursement request
	@PutMapping("/supervisorStatus")
	public ReimbursementModel updateSuperStatus(@RequestBody ReimbursementModel reimburse)
	{
		logger.info("updateSuperStatus() of reimbursementController is called!");
		return reimburseService.updateSupervisorStatus(reimburse);
	}
	
	///api from where accountant can approved or delete reimbursement request
	@PutMapping("/accountantStatus")
	public ReimbursementModel updateAccountantStatus(@RequestBody ReimbursementModel reimburse)
	{
		logger.info("updateAccountantStatus() of reimbursementController is called!");
		return reimburseService.updateAccountantStatus(reimburse);
	}
	//not in use
	@GetMapping("/totaltravelexpense")
	public long getTotalTravelExpense()
	{
		logger.info("getTotalTravelExpense() of reimbursementController is called!");
		return reimburseService.getTravelExpenses();
	}
	
	//displaying all reimburse to admin
	@GetMapping("/totalExpense")
	public List<ReimbursementModel> getTotalExpense()
	{
		logger.info("getTotalExpense() of reimbursementController is called!");
		return reimburseService.allExpense();
	}
	
	//displaying all reimbursement to the supervisor
	@GetMapping("/totalReimburse/{id}")
	public List<ReimbursementModel> getsuperReimburse(@PathVariable int id)
	{
		logger.info("getsuperReimburse() of reimbursementController is called!");
		return reimburseService.findBySuperId(id);
	}
	//displaying all the reimbursement to accountant
	@GetMapping("/accountReimburse")
	public List<ReimbursementModel> getAccountanntReimburse()
	{
		logger.info("getAccountanntReimburse() of reimbursementController is called!");
		return reimburseService.findAccountant();
	}
	//displaying reimbursement to employee
	@GetMapping("/employeeReimburse/{id}")
	public List<ReimbursementModel> getEmployeeReimburse(@PathVariable long id)
	{
		logger.info("getEmployeeReimburse() of reimbursementController is called!");
		return reimburseService.getEmployeeReimburse(id);
	}
	//deleting reimbursement on the basis of reimbursement id
	@DeleteMapping("/removeReimburse/{id}")
	public List<ReimbursementModel> removeReimburse(@PathVariable long id)
	{
		logger.info("removeReimburse() of reimbursementController is called!");
		return reimburseService.getEmployeeReimburse(id);
	}
	
	@PostMapping("/addAccountant")
	public AccountantModel addAccountant(@RequestBody AccountantModel accountant)
	{
		logger.info("addAccountant() of reimbursementController is called!");
		return accountService.addAccountant(accountant);
	}
	@PostMapping("/loginAccountant")
	public AccountantModel loginAccountant(@RequestBody AccountantModel accountant)
	{
		logger.info("loginAccountant() of reimbursementController is called!");
		return accountService.accountantLogin(accountant);
	}
	@PostMapping("/saveAttendence")
	public AttendanceModel saveAttendance(@RequestBody AttendanceModel attendance)
	{
		return attendService.saveAttendence(attendance);
	}

	@DeleteMapping("/deleteAttendence/{id}")
	private void deleteAttendence(@PathVariable int id) {
		attendService.deleteAttendence(id);
	}

	@PutMapping("/updateAttendence")
	private  AttendanceModel updateAttendence(@RequestBody  AttendanceModel attendence) {
		return attendService.update(attendence);
	}

	@GetMapping("/getAttendence/{id}")
	private  AttendanceModel getAttendence(@PathVariable int id) {
		return attendService.getAttendence(id);
	}

	@GetMapping("/get")
	private List< AttendanceModel> getAllAttendance() {
		return attendService.getAll();
	}
	
	@GetMapping("/getEmployeeAttendance/{employeeId}")
	public List<AttendanceModel> getEmployeeAttendance(@PathVariable long employeeId)
	{
		return attendService.getEmployeeAttendance(employeeId);
	}
	
	@PutMapping("/LogoutEmployee/{id}")
	public AttendanceModel loginAdmin(@PathVariable long id ) throws ParseException
	{
		return attendService.updateAttendance(id);
	}
	
	//api for displaying approved request to supervisor
	@GetMapping("/supervisorApproved/{superVisorId}")
	public List<ReimbursementModel> aprrovedSupervisor(@PathVariable int superVisorId)
	{
		logger.info("aprrovedSupervisor() of reimbursementController is called!");
		List<ReimbursementModel> allReimburse=reimburseService.aprrovedSupervisor(superVisorId);
		return allReimburse;
	}
	//api for displaying disapproved request to supervisor
	@GetMapping("/supervisorDisApproved/{superVisorId}")
	public List<ReimbursementModel> DisaprrovedSupervisor(@PathVariable int superVisorId)
	{
		logger.info("DisaprrovedSupervisor() of reimbursementController is called!");
		List<ReimbursementModel> allReimburse=reimburseService.disaprrovedSupervisor(superVisorId);
		return allReimburse;
	}
	
	//api for displaying approved request to Accountant
	@GetMapping("/allAccountantApproved")
	public List<ReimbursementModel> allAccountantApproved()
	{
		logger.info("allAccountantApproved() of reimbursementController is called!");
		List<ReimbursementModel> allReimburse=reimburseService.allAccountantApproved();
		return allReimburse;
	}
	
	//api for displaying disapproved request to Accountant
	@GetMapping("/allAccountantDisApproved")
	public List<ReimbursementModel> allAccountantDisApproved()
	{
		logger.info("allAccountantDisApproved() of reimbursementController is called!");
		List<ReimbursementModel> allReimburse=reimburseService.allAccountantDisApproved();
		return allReimburse;
	}
	
	//api for displaying reimburse on the basis of reimburseId
	@GetMapping("/reimburseById/{id}")
	public ReimbursementModel findReimburseById(@PathVariable long id)
	{
		logger.info("findReimburseById() of reimbursementController is called!");
		return reimburseService.findReimburseById(id);
		
	}
	
	@GetMapping("/monthlyReimburse/{month}/{year}")
	public List<ReimbursementModel> findByMonth(@PathVariable String month,@PathVariable String year)
	{
		logger.info("findByMonth() of reimbursementController is called!");
		return reimburseService.findByMonth(month, year);
	}

	
}
