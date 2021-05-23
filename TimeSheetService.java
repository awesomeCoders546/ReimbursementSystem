package com.sjp.service;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Model.EmployeeModel;

import com.sjp.Model.TimeSheetModel;
import com.sjp.Repository.EmployeeRepository;
import com.sjp.Repository.TimeSheetRepository;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;


@Service
public class TimeSheetService {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeSheetService.class);
	
	@Autowired
	private TimeSheetRepository timeSheetRepo;
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public TimeSheetModel saveTime(TimeSheetModel timeSheet) throws ParseException {
		logger.info("saveTime of timeSheetService is called!");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		 LocalDateTime now = LocalDateTime.now();
		 String sDate1=dtf.format(now);
		 Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(sDate1);
		 Format f = new SimpleDateFormat("HH.mm.ss Z");
		 f = new SimpleDateFormat("EEEE");
		 String str = f.format(new Date());
		 timeSheet.setDate(date1);
		 timeSheet.setDay(str);
		timeSheet.setAccountantApproved("waiting");
		timeSheet.setSupervisorApproved("waiting");
		 Optional<EmployeeModel> employee=employeeRepo.findById(timeSheet.getEmployeeId());
		EmployeeModel emp=employee.get();
		timeSheet.setEmployeeName(emp.getName());
		return timeSheetRepo.save(timeSheet);
	}
	public TimeSheetModel getTimeSheetBytimeSheetId(int id) {
		logger.info("getTimeSheetBytimeSheetId() of timeSheetService is called!");
		return timeSheetRepo.findById(id).get();
		
	}
	public List<TimeSheetModel> getTimeSheetByemployeeId(long id) {
		logger.info("getTimeSheetByemployeeId() of timeSheetService is called!");
		return timeSheetRepo.findByEmployeeId(id);
	}

	public List<TimeSheetModel> getAllTimeSheet() {
		logger.info("getAllTimeSheet() of timeSheetService is called!");
		List<TimeSheetModel> list=new ArrayList<TimeSheetModel>();
		list=timeSheetRepo.findAll();
		return list;
	}
	public TimeSheetModel update(TimeSheetModel timeSheet) {
		logger.info("update() of timeSheetService is called!");
		Optional<TimeSheetModel> a=timeSheetRepo.findById(timeSheet.getTimeSheetId());
		TimeSheetModel s=a.get();
		s.setTask(timeSheet.getTask());
	    s.setProjectName(timeSheet.getProjectName());
		return timeSheetRepo.save(s);
		}
	public void deleteTimeSheet(int id) {
		logger.info("deleteTimeSheet() of timeSheetService is called!");
		timeSheetRepo.deleteById(id);
	}
	public List<TimeSheetModel> superWaiting(long superId)
	{
		logger.info("deleteTimeSheet() of timeSheetService is called!");
		return timeSheetRepo.findAllWaiting(superId);
	}
	public List<TimeSheetModel> accountantApproved()
	{
		logger.info(" accountantApproved()() of timeSheetService is called!");
		return timeSheetRepo.findAccountantApproval();
	}
	
	public List<TimeSheetModel> superApproved(long superId)
	{
		logger.info(" superApproved() of timeSheetService is called!");
		return timeSheetRepo.findAllSuperVisorApproved(superId);
	}
	public List<TimeSheetModel> superDisapproved(long superId)
	{
		logger.info(" superDisapproved() of timeSheetService is called!");

		return timeSheetRepo.findAllSuperVisorDisApproved(superId);
	}
	public List<TimeSheetModel> allAccountantApproved()
	{
		logger.info(" allAccountantApproved() of timeSheetService is called!");
		return timeSheetRepo.findAllAccountantApproved();
	}
	
	public List<TimeSheetModel> allAccountantDisApproved()
	{
		logger.info(" allAccountantDisApproved() of timeSheetService is called!");
		return timeSheetRepo.findAllAccountantDisApproved();
	}
	
	public TimeSheetModel updateTimeSupervisorStatus(TimeSheetModel timeSheet)
	{
		logger.info(" updateTimeSupervisorStatus() of timeSheetService is called!");
		Optional<TimeSheetModel> reimburseM=timeSheetRepo.findById(timeSheet.getTimeSheetId());
		TimeSheetModel model=reimburseM.get();
		model.setSupervisorApproved(timeSheet.getSupervisorApproved());
		return timeSheetRepo.save(model);		
	}
	
	public TimeSheetModel updateTimeAccountantStatus(TimeSheetModel timeSheet)
	{	
	
		logger.info(" updateTimeAccountantStatus() of timeSheetService is called!");

		Optional<TimeSheetModel> reimburseM=timeSheetRepo.findById(timeSheet.getTimeSheetId());
		TimeSheetModel model=reimburseM.get();
		model.setAccountantApproved(timeSheet.getAccountantApproved());
		return timeSheetRepo.save(model);		
	
	}
	public List<TimeSheetModel> findByWeek()
	{
		logger.info(" findByWeek() of timeSheetService is called!");
		return timeSheetRepo.findByWeek();
	} 
	public List<TimeSheetModel> findByCoddingTask()
	{
		return timeSheetRepo.findAllCodding();
	}
	public List<TimeSheetModel> findByReqirementgatheringTask()
	{
		return timeSheetRepo.findAllRequirementGathering();
	} 
	public List<TimeSheetModel> findByTestingTask()
	{
		return timeSheetRepo.findAllTesting();
	} 
	public List<TimeSheetModel> findByMeetingTask()
	{
		return timeSheetRepo.findAllMeeting();
	} 
	public List<TimeSheetModel>  findByMonth(String month,String year)
	{
		logger.info(" findByMonth() of timeSheetService is called!");
		return timeSheetRepo.findByMonth(month, year);	
	}
	public List<TimeSheetModel> findByWeekAndEmployee(long id)
	{
		logger.info(" findByWeekAndEmployee() of timeSheetService is called!");
		TimeSheetModel timeSheet=new TimeSheetModel();
		int logHourW=0;
		List<TimeSheetModel> abc=timeSheetRepo.findByWeekAndEmployeId(id);
		for(TimeSheetModel timeS:abc) {
			logHourW+=timeS.getLogHours();	
		}
		timeSheet.setLogWeekHours(logHourW);
		abc.add(timeSheet);
		return abc;	
	
	}
	public List<TimeSheetModel>  findByMonthAndEmployee(long id,String month,String year)
	{
		logger.info(" findByMonthAndEmployee() of timeSheetService is called!");
		TimeSheetModel timeSheet=new TimeSheetModel();
		int logHourW=0;
		List<TimeSheetModel> abc=timeSheetRepo.findByMonthAndEmployeeId(id, month, year);
		for(TimeSheetModel timeS:abc) {
			logHourW+=timeS.getLogHours();	
		}
		timeSheet.setLogMonthHours(logHourW);
		abc.add(timeSheet);
		return abc;	
	}
	public List<TimeSheetModel> findByDay(String timeDate)
	{
		logger.info(" findByDay() of timeSheetService is called!");
		return timeSheetRepo.findByDay(timeDate);
	}
	public List<TimeSheetModel> findByDayAndEmployeeId(String timeDate,long employeeId)
	{
		logger.info(" findByDayAndEmployeeId() of timeSheetService is called!");
		List<TimeSheetModel> timeList=timeSheetRepo.findByDayAndEmployeeId(timeDate, employeeId);
		int daily=0;
		TimeSheetModel timeModel=new TimeSheetModel();
		for(TimeSheetModel timeSheetList:timeList)
		{
			daily+=timeSheetList.getLogHours();
		}
	    timeModel.setLogDayHours(daily);
	    timeList.add(timeModel);
	    return timeList;
	    }
	public List<TimeSheetModel> findByTask(String task)
	{
		logger.info(" findByTask() of timeSheetService is called!");
		return timeSheetRepo.findByTask(task);
	}
	public List<TimeSheetModel> findByTaskandEmployeeId(String task,long employeeId)
	{
		logger.info(" findByTaskandEmployeeId() of timeSheetService is called!");
		List<TimeSheetModel> timeList=timeSheetRepo.findByTaskAndEmployeeId(task,employeeId);;
		int daily=0;
		TimeSheetModel timeModel=new TimeSheetModel();
		for(TimeSheetModel timeSheetList:timeList)
		{
			daily+=timeSheetList.getLogHours();
		}
	    timeModel.setLogDayHours(daily);
	    timeList.add(timeModel);
		return timeList;
	}
	public List<TimeSheetModel> findByProject(String projectName)
	{
		logger.info(" findByProject() of timeSheetService is called!");
		return timeSheetRepo.findByProjectName(projectName);
	}
	public List<TimeSheetModel> findByprojectNameandEmployeeId(String projectName,long employeeId)
	{
		logger.info(" findByprojectNameandEmployeeId() of timeSheetService is called!");
		List<TimeSheetModel> timeList=timeSheetRepo.findByProjectNameAndEmployeeId(projectName,employeeId);
		int daily=0;
		TimeSheetModel timeModel=new TimeSheetModel();
		for(TimeSheetModel timeSheetList:timeList)
		{
			daily+=timeSheetList.getLogHours();
		}
	    timeModel.setLogDayHours(daily);
	    timeList.add(timeModel);
		return timeList;
	}
	public List<TimeSheetModel> findWeekLyProject(String startDate,String endDate)
	{
		logger.info(" findWeekLyProject() of timeSheetService is called!");
		return timeSheetRepo.findByWeekly(startDate, endDate);
	}
	
	public List<TimeSheetModel> findWeekLyProjectAndEmployeeID(String startDate,String endDate,long employeeId)
	{
		logger.info(" findWeekLyProjectAndEmployeeID() of timeSheetService is called!");
		TimeSheetModel timeSheet=new TimeSheetModel();
		int logHourW=0;
		List<TimeSheetModel> abc=timeSheetRepo.findByWeeklyAndEmployeeId(startDate, endDate, employeeId);
		for(TimeSheetModel timeS:abc) {
			logHourW+=timeS.getLogHours();	
		}
		timeSheet.setLogWeekHours(logHourW);
		abc.add(timeSheet);
		return abc;	
	}
	
	public List<TimeSheetModel> findBySuperWeek(long superId)
	{
		return timeSheetRepo.findBySuperWeek(superId);
	}
	public List<TimeSheetModel> findSuperWeekAndEmployee(long superId,long employeeId)
	{
		logger.info(" findSuperWeekAndEmployee() of timeSheetService is called!");
		TimeSheetModel timeSheet=new TimeSheetModel();
		int logHourW=0;
		List<TimeSheetModel> abc=timeSheetRepo.findBySuperWeekAndEmployee(superId, employeeId);
		for(TimeSheetModel timeS:abc) {
			logHourW+=timeS.getLogHours();	
		}
		timeSheet.setLogWeekHours(logHourW);
		abc.add(timeSheet);
		return abc;	
	}
	public List<TimeSheetModel> findSuperWeekDuration(String startDate,String endDate,long superId)
	{
		return timeSheetRepo.findBySuperWeekly(startDate, endDate, superId);
		
	}
	public List<TimeSheetModel> findSuperWeeDurationEmployee(String startDate,String endDate,long superId,long employeeId)
	{
		TimeSheetModel timeSheet=new TimeSheetModel();
		int logHourW=0;
		List<TimeSheetModel> abc=timeSheetRepo.findBySuperWeeklyAndEmployee(startDate, endDate, superId, employeeId);
		for(TimeSheetModel timeS:abc) {
			logHourW+=timeS.getLogHours();	
		}
		timeSheet.setLogWeekHours(logHourW);
		System.out.println(logHourW);
		abc.add(timeSheet);
		return abc;	
	}
	public List<TimeSheetModel> saveMultipleTime(List<TimeSheetModel> multileTimeSheet)
	{

		for (int t = 0; t < multileTimeSheet.size(); t++) {

			multileTimeSheet.get(t).setAccountantApproved("waiting");
			multileTimeSheet.get(t).setSupervisorApproved("waiting");
			Optional<EmployeeModel> employee = employeeRepo.findById(multileTimeSheet.get(t).getEmployeeId());
			EmployeeModel emp = employee.get();
			multileTimeSheet.get(t).setEmployeeName(emp.getName());
			Format f = new SimpleDateFormat("HH.mm.ss Z");
			f = new SimpleDateFormat("EEEE");
			String str = f.format(multileTimeSheet.get(t).getDate());
			multileTimeSheet.get(t).setDay(str);

		}

		return timeSheetRepo.saveAll(multileTimeSheet);
	}
	
	public List<TimeSheetModel> updateMultipleSupervisor(List<TimeSheetModel> multipleTimeSheet)
	{
		for(TimeSheetModel timeSheet:multipleTimeSheet)
		{
			System.out.println(timeSheet.getTimeSheetId());
			timeSheetRepo.updateSuperStatus(timeSheet.getSupervisorApproved(), timeSheet.getTimeSheetId());
		}
			
		return multipleTimeSheet;
	}
	public List<TimeSheetModel> updateMultipleAccountant(List<TimeSheetModel> multipleTimeSheet)
	{
		for(TimeSheetModel timeSheet:multipleTimeSheet)
		{
			System.out.println(timeSheet.getTimeSheetId());
			System.out.println(timeSheet.getAccountantApproved());
			timeSheetRepo.updateAccountantStatus(timeSheet.getAccountantApproved(), timeSheet.getTimeSheetId());
		}
			
		return multipleTimeSheet;
	}

}
