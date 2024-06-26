package com.motta.attendance_service.mapper;

import com.motta.attendance_service.entity.Attendance;
import com.motta.attendance_service.model.AttendanceDTO;

public class AttendanceMapper {

	// Convert Attendance JPA Entity into AttendanceDTO
	public static AttendanceDTO mapToAttendanceDTO(Attendance attendance) {
		AttendanceDTO attendanceDTO = new AttendanceDTO(attendance.getId(), attendance.getNumberOfWorkingDays(),
				attendance.getCreatedAt(), attendance.getModifiedAt(), attendance.getEmployeeId());
		return attendanceDTO;
	}

	// Convert AttendanceDTO into Attendance JPA Entity
	public static Attendance mapToAttendance(AttendanceDTO attendanceDTO) {
		Attendance attendance = new Attendance(attendanceDTO.getId(), attendanceDTO.getNumberOfWorkingDays(),
				attendanceDTO.getCreatedAt(), attendanceDTO.getModifiedAt(), attendanceDTO.getEmployeeId());
		return attendance;
	}
}
