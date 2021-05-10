package com.sjp.service;


import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

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
	
	public AttendanceModel updateAttendance(long id)
	{
		 AttendanceModel model= attendanceRepo.findLogoutTime(id);
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		
		    System.out.println(model.getLoginTime());
			int loginTime=model.getLoginTime().getHours();
			int loginMinute=model.getLoginTime().getMinutes();
			LocalDateTime now = LocalDateTime.now();
			int logoutHours=now.getHour();
			int logminutes=now.getMinute();
			int logMinutesop=-(logminutes-loginMinute);
			System.out.println(-(logminutes-loginMinute));
			System.out.println(dtf.format(now));  
			 model.setLogoutTime(dtf.format(now));
			 int totalLogHours=logoutHours-loginTime;
			 String logHours=totalLogHours+" hours "+logMinutesop+" minutes ";
			 model.setLogHours(logHours);
			 attendanceRepo.save(model);
			  return model;
	}
}
