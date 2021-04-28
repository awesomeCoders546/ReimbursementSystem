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
	s.setComment(attendence.getComment());
	return attendanceRepo.save(s);
	}
     
	public List<AttendanceModel> getEmployeeAttendance(int employeeId)
	{
		return attendanceRepo.findByEmployeeId(employeeId);
	}
	
	public AttendanceModel updateAttendance(int id)
	{
	    AttendanceModel model= attendanceRepo.findLogoutTime(id);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	//  String time=model.getLoginTime().;
		int loginTime=model.getLoginTime().getMinutes();
		System.out.println("loginntime" + loginTime);
		LocalDateTime now = LocalDateTime.now();
		int logoutHours=now.getMinute();
		System.out.println(dtf.format(now));  
		System.out.println("logoutTime"+logoutHours);
		 model.setLogoutTime(dtf.format(now));
		 int totalLogHours=logoutHours-loginTime;
		 System.out.println("total log hours"+totalLogHours);
		 model.setLogHours(totalLogHours);
		 attendanceRepo.save(model);
		  return model;
	}
}
