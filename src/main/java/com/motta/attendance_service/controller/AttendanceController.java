package com.motta.attendance_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motta.attendance_service.model.AttendanceDTO;
import com.motta.attendance_service.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	// create Attendance REST API
	@PostMapping("/attendances")
	public ResponseEntity<AttendanceDTO> createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
		AttendanceDTO savedAttendance = attendanceService.createAttendance(attendanceDTO);
		return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
	}

	// Retrieve Attendance by id REST API
	@GetMapping("/attendances/{id}")
	public ResponseEntity<AttendanceDTO> retrieveAttendanceById(@PathVariable("id") Integer id) {
		AttendanceDTO attendance = attendanceService.retrieveAttendanceById(id);
		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}

	// Retrieve Attendance by id using RequestParam REST API
	// For example, http://localhost:8080/attendance?id=10001
	@GetMapping("/attendance")
	public ResponseEntity<AttendanceDTO> retrieveAttendanceByIdRequestParam(@RequestParam Integer id) {
		AttendanceDTO attendance = attendanceService.retrieveAttendanceById(id);
		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}

	// Retrieve All Salaries REST API
	@GetMapping("/attendances")
	public ResponseEntity<List<AttendanceDTO>> retrieveAllAttendances() {
		List<AttendanceDTO> attendances = attendanceService.retrieveAllAttendances();
		return new ResponseEntity<>(attendances, HttpStatus.OK);
	}

	// Update Attendance REST API
	@PutMapping("/attendances/{id}")
	public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("id") Integer id,
			@RequestBody AttendanceDTO attendance) {
		attendance.setId(id);
		AttendanceDTO updatedAttendance = attendanceService.updateAttendance(attendance);
		return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
	}

	// Delete Attendance REST API
	@DeleteMapping("/attendances/{id}")
	public ResponseEntity<String> deleteAttendance(@PathVariable("id") Integer id) {
		attendanceService.deleteAttendance(id);
		return new ResponseEntity<>("Attendance successfully deleted!", HttpStatus.OK);
	}

	// Retrieve All Attendances by employeeId REST API
	@GetMapping("/attendancesByEmployeeId/{employeeId}")
	public ResponseEntity<AttendanceDTO> getAllAttendancesByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
		AttendanceDTO attendanceDTO = attendanceService.retrieveAttendanceByEmployeeId(employeeId);
		return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
	}
}
