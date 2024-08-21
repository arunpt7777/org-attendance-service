package com.motta.attendance_service.mapper;

import com.motta.attendance_service.entity.Attendance;
import com.motta.attendance_service.model.AttendanceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

	// Convert Scheme JPA Entity into SchemeDTO
	public AttendanceDTO mapToAttendanceDTO(Attendance attendance) {
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		BeanUtils.copyProperties(attendance, attendanceDTO);
		return attendanceDTO;
	}

	// Convert SchemeDTO into Scheme JPA Entity
	public Attendance mapToAttendance(AttendanceDTO attendanceDTO) {
		Attendance attendance = new Attendance();
		BeanUtils.copyProperties(attendanceDTO, attendance);
		return attendance;
	}
}
