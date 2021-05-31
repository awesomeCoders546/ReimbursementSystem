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
import com.sjp.Model.TimeSheetModel;
import com.sjp.Repository.AdminLoginRepo;
import com.sjp.service.AccountantService;
import com.sjp.service.AttendanceService;
import com.sjp.service.DepartmentService;
import com.sjp.service.EmployeeService;
import com.sjp.service.ReimburseService;
import com.sjp.service.SuperVisorService;
import com.sjp.service.TimeSheetService;
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
	private TimeSheetService timeService;
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
		logger.info(" getAllAttendance()");
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
	@PostMapping("/SaveTimeSheet")
	public TimeSheetModel saveTimeSheet(@RequestBody TimeSheetModel timeSheet) throws ParseException
	{
		logger.info("saveTimeSheet() of reimbursementController is called! ");
		return timeService.saveTime(timeSheet);
	}
	
	@GetMapping("/TimeSheetbyTId/{timeSheetId}")
	public TimeSheetModel getTimeSheetById(@PathVariable int timeSheetId)
	{
		logger.info("getTimeSheetById() of reimbursementController is called! ");
		return timeService.getTimeSheetBytimeSheetId(timeSheetId);
	}
	@GetMapping("/TimeSheetbyEId/{employeeId}")
	public List<TimeSheetModel> getEmployeeById(@PathVariable long employeeId)
	{
		logger.info("getEmployeeById() of reimbursementController is called! ");
		return timeService.getTimeSheetByemployeeId(employeeId);
	}
	
	@GetMapping("/timeSheet")
	private List< TimeSheetModel> getAll() {
		logger.info(" getAll() of reimbursementController is called! ");
		return timeService.getAllTimeSheet();
	}
	
	@PutMapping("/updateTimeSheet")
	private  TimeSheetModel updateTimeSheet(@RequestBody  TimeSheetModel timeSheet) {
		logger.info(" updateTimeSheet() of reimbursementController is called! ");
		return timeService.update(timeSheet);
	}
	@DeleteMapping("/deleteTimeSheet/{id}")
	public void deleteTimeSheetId(@PathVariable int id)
	{
		logger.info("deleteTimeSheetId() of reimbursementController is called! ");
		timeService.deleteTimeSheet(id);
		}
	@GetMapping("/superwaiting/{id}")
	public List<TimeSheetModel> superWaiting(@PathVariable long id)
	{
		logger.info("superWaiting() of reimbursementController is called! ");
		return timeService.superWaiting(id);
	}
	
	@GetMapping("/accountantapproved")
	public List<TimeSheetModel> accountantApproved()
{
		logger.info("accountantApproved() of reimbursementController is called! ");
		return timeService.accountantApproved();
	}
	
	@GetMapping("/superapproved/{id}")
	public List<TimeSheetModel> superApproved(@PathVariable long id)
	{
		logger.info("superApproved() of reimbursementController is called! ");
		return timeService.superApproved(id);
	}
	@GetMapping("/superdisapproved/{id}")
	public List<TimeSheetModel> superDisapproved(@PathVariable long id)
	{
		
		logger.info("superDisapproved() of reimbursementController is called! ");
		return timeService.superDisapproved(id);
	}
	@GetMapping("/allaccountantapproved")
	public List<TimeSheetModel> allaccountantApproved()
	{
		logger.info("allaccountantApproved() of reimbursementController is called! ");
		return timeService.allAccountantApproved();
	}
	@GetMapping("/allaccountantDisapproved")
	public List<TimeSheetModel> allaccountantDisApproved()
	{
		logger.info("allaccountantDisApproved() of reimbursementController is called! ");
		return timeService.allAccountantDisApproved();
	}
	
	@PutMapping("/upadteTimeSuperApproved")
	public TimeSheetModel updateTimeSupervisorStatus(@RequestBody TimeSheetModel timeSheet)
	{
		logger.info("updateTimeSupervisorStatus() of reimbursementController is called! ");
		return timeService.updateTimeSupervisorStatus(timeSheet);
	}
	@PutMapping("/upadteTimeAccountantApproved")
	public TimeSheetModel updateTimeaccountantStatus(@RequestBody TimeSheetModel timeSheet)
	{
		logger.info("updateTimeaccountantStatus() of reimbursementController is called! ");
		return timeService.updateTimeAccountantStatus(timeSheet);
	}
	@GetMapping("/findbyWeek")
	public List<TimeSheetModel> findWeekly()
	{
		logger.info("findWeekly() of reimbursementController is called! ");
		return  timeService.findByWeek();
	}
	@GetMapping("/findbycoddingTask")
	public List<TimeSheetModel> findCodding()
	{
		logger.info("findWeekly() of reimbursementController is called! ");
		return  timeService.findByCoddingTask();
	}
	@GetMapping("/findbyrequirementgatheringTask")
	public List<TimeSheetModel> findRequirementGathering()
	{
		return  timeService.findByReqirementgatheringTask();
	}
	@GetMapping("/findbytestingTask")
	public List<TimeSheetModel> findTesting()
	{
		return  timeService.findByTestingTask();
	}
	@GetMapping("/findbymeetingTask")
	public List<TimeSheetModel> findMeeting()
	{
		return  timeService.findByMeetingTask();
	}
	@GetMapping("/timeSheetByMonth/{month}/{year}")
	public List<TimeSheetModel> findTimeSheet(@PathVariable String month,@PathVariable String year)
	{
		logger.info("findTimeSheet() of reimbursementController is called! ");
		return timeService.findByMonth(month, year);
	}
	@GetMapping("/findbyWeekandemployeid/{id}")
	public List<TimeSheetModel> findWeekAndEmployee(@PathVariable long id)
	{
		logger.info("findTimeSheet() of reimbursementController is called! ");
		return  timeService.findByWeekAndEmployee(id);
	}
	@GetMapping("/timeSheetByMonthandemployee/{id}/{month}/{year}")
	public List<TimeSheetModel> findTimeSheetMonthAndEmployee(@PathVariable long id,@PathVariable String month,@PathVariable String year)
	{
		logger.info("findTimeSheetMonthAndEmployee() of reimbursementController is called! ");
		return timeService.findByMonthAndEmployee(id, month, year);
	}
	@GetMapping("/timeSheetBydate/{timeDate}")
	public List<TimeSheetModel> findTimeSheetbyDate(@PathVariable String timeDate)
	{
		logger.info("findTimeSheetbyDate() of reimbursementController is called! ");
		return timeService.findByDay(timeDate);
	}
	@GetMapping("/timeSheetBydateAndEmployeeId/{timeDate}/{employeeId}")
	public List<TimeSheetModel> findTimeSheetbyDateAndEmployeeId(@PathVariable String timeDate,@PathVariable long employeeId)
	{
		logger.info("findTimeSheetbyDateAndEmployeeId");
		return timeService.findByDayAndEmployeeId(timeDate, employeeId);
	}
	@GetMapping("/timeSheetByTask/{task}")
	public List<TimeSheetModel> findByTask(@PathVariable String task)
	{
		logger.info("findByTaskd() of reimbursementController is called!");
		return timeService.findByTask(task);
	}
	@GetMapping("/timeSheetByTask/{task}/{employeeId}")
	public List<TimeSheetModel> findByTaskandEmployeeId(@PathVariable String task,@PathVariable long employeeId)
	{
		logger.info("findByTaskandEmployeeId() of reimbursementController is called!");
		return timeService.findByTaskandEmployeeId(task, employeeId);
	}
	@GetMapping("/timeSheetByprojectName/{projectName}")
	public List<TimeSheetModel> findByProject(@PathVariable String projectName)
	{
		logger.info("findByProject() of reimbursementController is called!");
		return timeService.findByProject(projectName);
	}
	@GetMapping("/timeSheetByprojectNameAndId/{projectName}/{employeeId}")
	public List<TimeSheetModel> findByprojectNameandEmployeeId(@PathVariable String projectName,@PathVariable long employeeId)
	{
		logger.info(" findByprojectNameandEmployeeId() of reimbursementController is called!");
		return timeService.findByprojectNameandEmployeeId(projectName, employeeId);
	}
	@GetMapping("/weeklyReport/{startDate}/{endDate}")
	public List<TimeSheetModel> findWeekLyProject(@PathVariable String startDate,@PathVariable String endDate)
	{
		logger.info(" findWeekLyProject() of reimbursementController is called!");
		return timeService.findWeekLyProject(startDate, endDate);
	}
	
	@GetMapping("/weeklyReportOfEmployee/{startDate}/{endDate}/{employeeId}")
	public List<TimeSheetModel> findWeekLyProjectOfEmployee(@PathVariable String startDate,@PathVariable String endDate,@PathVariable long employeeId)
	{
		logger.info(" findWeekLyProjectOfEmployee() of reimbursementController is called!");
		return timeService.findWeekLyProjectAndEmployeeID(startDate, endDate, employeeId);
	}
	@GetMapping("/superWeek/{superId}")
	public List<TimeSheetModel> findBySuperId(@PathVariable long superId)
	{
		return timeService.findBySuperWeek(superId);
	}
	@GetMapping("/superWeeklyAndEmployee/{superId}/{employeeId}")
	public List<TimeSheetModel> findBySuperIdAndEmployeeId(@PathVariable long superId,@PathVariable long employeeId)
	{
		return timeService.findSuperWeekAndEmployee(superId, employeeId);
	}
	
	@GetMapping("/superWeeklyAndEmployee/{startDate}/{endDate}/{superId}")
	public List<TimeSheetModel> findBySuperDurationAndEmployee(@PathVariable String startDate,@PathVariable String endDate,@PathVariable long superId)
	{
		return timeService.findSuperWeekDuration(startDate, endDate, superId);
	}
	
	@GetMapping("/superWeeklyAndEmployee/{startDate}/{endDate}/{superId}/{employeeId}")
	public List<TimeSheetModel> findBySuperWeekAndEmployee(@PathVariable String startDate,@PathVariable String endDate,@PathVariable long superId,@PathVariable long employeeId)
	{
		return timeService.findSuperWeeDurationEmployee(startDate, endDate, superId, employeeId);
	}
	
	@PostMapping("/saveMultipleTimesheet")
	public List<TimeSheetModel> saveMultipleTime(@RequestBody List<TimeSheetModel> multileTimeSheet)
	{
		return timeService.saveMultipleTime(multileTimeSheet);
	}
	
	@PutMapping("/updateMultipleSupervisorStaus")
	public List<TimeSheetModel> updateMultipleSupervisor(@RequestBody List<TimeSheetModel> multipleTimeSheet)
	{
		return timeService.updateMultipleSupervisor(multipleTimeSheet);
	}
	@PutMapping("/updateMultipleAccountantStaus")
	public List<TimeSheetModel> updateMultipleAccountant(@RequestBody List<TimeSheetModel> multipleTimeSheet)
	{
		return timeService.updateMultipleAccountant(multipleTimeSheet);
	}
	@GetMapping("/accountWeekDefault")
	public List<TimeSheetModel> accountDefaultWeek()
	{
		return timeService.accountDefaultWeek();
	}
	@GetMapping("/accountWeekDefaultByEmployeeId")
	public List<TimeSheetModel> accountEmployeeWeek(long employeeId)
	{
		return timeService.accountEmployeeWeek(employeeId);
	}
	@GetMapping("/adminCurrentWeek")
	public List<TimeSheetModel> adminCurrentWeek()
	{
		return timeService.adminCurrentWeek();
	}
	@GetMapping("/adminWeeklyTimesheet/{startDate}/{endDate}")
	public List<TimeSheetModel> findByAdminWeeklyTimesheet(@PathVariable String startDate,@PathVariable String endDate)
	{
		return timeService.findByAdminWeeklyTimesheet(startDate, endDate);
	}
	@GetMapping("/adminWeeklyTimesheetEmployee/{startDate}/{endDate}/{employeeId}")
	public List<TimeSheetModel> findByAdminWeeklyTimesheetE(@PathVariable String startDate,@PathVariable String endDate, @PathVariable long employeeId)
	{
		return timeService.findByAdminWeeklyTimesheetE(startDate, endDate, employeeId);
	}
	@GetMapping("/accWeeklyAndEmployee/{startDate}/{endDate}")
	public List<TimeSheetModel> findBySuperDurationAndEmployee(@PathVariable String startDate,@PathVariable String endDate)
	{
		return timeService.findAccWeekDuration(startDate, endDate);
	}
	
	@GetMapping("/accWeeklyAndEmployee/{startDate}/{endDate}/{employeeId}")
	public List<TimeSheetModel> findBySuperWeekAndEmployee(@PathVariable String startDate,@PathVariable String endDate, @PathVariable long employeeId)
	{
		return timeService.findAccWeeDurationEmployee(startDate, endDate, employeeId);
	}
	
	@GetMapping("/accCurrentWeek")
	public List<TimeSheetModel> accCurrentWeek()
	{
		return timeService.accCurrentWeek();
	}

	
}
     

