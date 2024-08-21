package com.motta.attendance_service.service;

import java.util.List;

import com.motta.attendance_service.model.AttendanceDTO;

public interface AttendanceService {

	AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);

	AttendanceDTO retrieveAttendanceById(Integer id);

	List<AttendanceDTO> retrieveAllAttendances();

	void deleteAttendance(Integer id);

	AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO);

	AttendanceDTO retrieveAttendanceByEmployeeId(int employeeId);

	void validateAttendanceDTO(AttendanceDTO  attendanceDTO);
}
