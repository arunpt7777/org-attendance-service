package com.motta.attendance_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motta.attendance_service.entity.Attendance;
import com.motta.attendance_service.exception.AttendanceAlreadyExistsException;
import com.motta.attendance_service.exception.AttendanceNotFoundException;
import com.motta.attendance_service.mapper.AttendanceMapper;
import com.motta.attendance_service.model.AttendanceDTO;
import com.motta.attendance_service.repository.AttendanceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AttendanceServiceImplementation implements AttendanceService {

	@Autowired
	private AttendanceRepository repository;

	@Override
	public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {

		// CHeck if id already exists
		Optional<Attendance> attendance = repository.findById(attendanceDTO.getId());
		if (attendance.isPresent())
			throw new AttendanceAlreadyExistsException("Attendance id = " + attendanceDTO.getId() + " already Exists!");

		// Convert AttendanceDTO into User JPA Entity
		Attendance newAttendance = AttendanceMapper.mapToAttendance(attendanceDTO);
		Attendance savedAttendance = repository.save(newAttendance);

		// Convert Attendance JPA entity to UserDto
		AttendanceDTO savedAttendanceDTO = AttendanceMapper.mapToAttendanceDTO(savedAttendance);
		return savedAttendanceDTO;
	}

	@Override
	public AttendanceDTO retrieveAttendanceById(Integer id) {
		Attendance attendance = repository.findById(id).get();
		if (attendance == null)
			throw new AttendanceNotFoundException("Attendance id = " + id + " not found. Please enter different id");
		return AttendanceMapper.mapToAttendanceDTO(attendance);
	}

	@Override
	public List<AttendanceDTO> retrieveAllAttendances() {
		List<Attendance> attendances = repository.findAll();
		return attendances.stream().map(AttendanceMapper::mapToAttendanceDTO).collect(Collectors.toList());
	}

	@Override
	public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO) {
		Attendance existingAttendance = repository.findById(attendanceDTO.getId()).get();
		if (existingAttendance == null)
			throw new AttendanceNotFoundException(
					"Attendance id = " + attendanceDTO.getId() + " not found. Please enter different id");

		existingAttendance.setNumberOfWorkingDays(attendanceDTO.getNumberOfWorkingDays());
		existingAttendance.setCreatedAt(attendanceDTO.getCreatedAt());
		existingAttendance.setModifiedAt(attendanceDTO.getModifiedAt());
		existingAttendance.setEmployeeId(attendanceDTO.getEmployeeId());

		Attendance updatedAttendance = repository.save(existingAttendance);
		return AttendanceMapper.mapToAttendanceDTO(updatedAttendance);
	}

	@Override
	public void deleteAttendance(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public AttendanceDTO retrieveAttendanceByEmployeeId(Integer employeeId) {
		List<Attendance> attendances = repository.findAll();
		return attendances.stream().filter(attendance -> attendance.getEmployeeId().equals(employeeId))
				.map(AttendanceMapper::mapToAttendanceDTO).collect(Collectors.toList()).getFirst();
	}

}
