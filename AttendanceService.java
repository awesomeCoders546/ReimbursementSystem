package com.sjp.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjp.Model.AttendanceModel;
import com.sjp.Model.EmployeeModel;
import com.sjp.Repository.AttendanceRepository;
import com.sjp.Repository.EmployeeRepository;

@Service
public class AttendanceService
{
	@Autowired
	private AttendanceRepository attendanceRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	public AttendanceModel saveAttendence(AttendanceModel attendance)
	{
		Optional<EmployeeModel> employee=employeeRepo.findById(attendance.getEmployeeId());
		EmployeeModel emp=employee.get();
		attendance.setEmployeeName(emp.getName());
		 return attendanceRepo.save(attendance);
	}
	
	public void deleteAttendence(int attendenceId) {
		attendanceRepo.deleteById(attendenceId);
	}
	
	public AttendanceModel getAttendence(int id) {
		return attendanceRepo.findById(id).get();
	}
	
	public List<AttendanceModel> getAll() {
		List<AttendanceModel> list=new ArrayList<AttendanceModel>();
		list=attendanceRepo.findAll();
		return list;
	}
	
//	public AttendenceModel getByEId(int eid) {
//		return attendanceRepo.findByEmployeeId(eid);l 
//	}
	
	public AttendanceModel update(AttendanceModel attendence) {
	Optional<AttendanceModel> a=attendanceRepo.findById(attendence.getAttendenceId());
	AttendanceModel s=a.get();
	s.setStatus(attendence.getStatus());
	
	return attendanceRepo.save(s);
	}
     
	public List<AttendanceModel> getEmployeeAttendance(long employeeId)
	{
	
		List<AttendanceModel> attend= attendanceRepo.findByEmployeeId(employeeId);
		for(AttendanceModel attenda2:attend)
		System.out.println(attenda2.getLoginTime());
		return attend;
	}
	
	public AttendanceModel updateAttendance(long id) throws ParseException
	{
		 AttendanceModel model= attendanceRepo.findLogoutTime(id);
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		   
		int logMinutesop=0;
		
		    System.out.println(model.getLoginTime());
			int loginTime=model.getLoginTime().getHours();
			int loginMinute=model.getLoginTime().getMinutes();
			LocalDateTime now = LocalDateTime.now();
			int logoutHours=now.getHour();
			int logminutes=now.getMinute();
			if(logminutes<loginMinute)
			{
			logMinutesop=-(logminutes-loginMinute);
			}
			else
			{
				logMinutesop=loginMinute-loginMinute;
			}
			 Date date1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dtf.format(now));  
			Duration timeLapse=Duration.between(model.getLoginTime().toInstant(), date1.toInstant());
			System.out.println("diffrence = "+timeLapse);
			System.out.println("time in String"+timeLapse.toMinutes());
			long timeoutHour=timeLapse.toMinutes()/60;
			long tieoutMinute=timeLapse.toMinutes()%60;
			System.out.println("timeout minutes:"+timeoutHour);
			System.out.println("minutes ="+tieoutMinute);
			System.out.println(-(logminutes-loginMinute));
			System.out.println(dtf.format(now));  
			 model.setLogoutTime(dtf.format(now));
			 int totalLogHours=logoutHours-loginTime;
			 String logHours=timeoutHour+" hours "+tieoutMinute+" minutes ";
			 model.setLogHours(logHours);
			 attendanceRepo.save(model);
			  return model;
	}
}
