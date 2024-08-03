package com.motta.attendance_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.motta.attendance_service.exception.InvalidAttendanceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class AttendanceServiceImplementation implements AttendanceService {

	private static final Logger log = LoggerFactory.getLogger(AttendanceServiceImplementation.class);

	@Value("${attendance.id.initialValue}")
	private Integer initialValueOfPrimaryKey;

	@Value("${attendance.working.days.min}")
	private Integer minWorkingDays;

	@Value("${attendance.working.days.max}")
	private Integer maxWorkingDays;

	@Autowired
	private AttendanceRepository repository;

	@Override
	public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {

		// CHeck if id already exists
		Optional<Attendance> attendance = repository.findById(attendanceDTO.getId());
		log.info("Attendance id = {} not found.", attendanceDTO.getId());

		if (attendance.isPresent())
			throw new AttendanceAlreadyExistsException("Attendance id = " + attendanceDTO.getId() + " already Exists!");

		// Convert AttendanceDTO into User JPA Entity
		Attendance newAttendance = AttendanceMapper.mapToAttendance(attendanceDTO);
		Attendance savedAttendance = repository.save(newAttendance);
		log.info("Attendance id = {} has been persisted.", newAttendance.getId());
        return AttendanceMapper.mapToAttendanceDTO(savedAttendance);
	}

	@Override
	public AttendanceDTO retrieveAttendanceById(Integer id) {
		Attendance attendance = repository.findById(id).get();
		log.error("Attendance id = {} not found. Please enter different id", id);
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
		log.info("Attendance id = {} has been fetched.", attendanceDTO.getId());

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


	@Override
	public void validateAttendanceDTO(AttendanceDTO  attendanceDTO) {

		if (attendanceDTO.getId()==null) {
			throw new InvalidAttendanceException("Attendance Id is mandatory");
		}

		if (attendanceDTO.getId()<initialValueOfPrimaryKey) {
			throw new InvalidAttendanceException("Attendance Id must not be less than the initial value of: " + initialValueOfPrimaryKey);
		}

		if (attendanceDTO.getEmployeeId()==null) {
			throw new InvalidAttendanceException("Employee ID is mandatory");
		}
		if (attendanceDTO.getNumberOfWorkingDays()==null) {
			throw new InvalidAttendanceException("Number of working days is mandatory");
		}

		if (attendanceDTO.getNumberOfWorkingDays()<minWorkingDays || attendanceDTO.getNumberOfWorkingDays()>maxWorkingDays) {
			throw new InvalidAttendanceException("Number of working days must be between " + minWorkingDays + " and " + maxWorkingDays);
		}

		if (attendanceDTO.getCreatedAt()==null) {
			throw new InvalidAttendanceException("Created At is mandatory");
		}

		if (attendanceDTO.getModifiedAt()==null) {
			throw new InvalidAttendanceException("Modified At is mandatory");
		}
	}


}
