package com.motta.attendance_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.motta.attendance_service.exception.InvalidAttendanceException;
import lombok.extern.slf4j.Slf4j;
import static com.motta.attendance_service.util.AttendanceConstants.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.motta.attendance_service.entity.Attendance;
import com.motta.attendance_service.exception.AttendanceAlreadyExistsException;
import com.motta.attendance_service.mapper.AttendanceMapper;
import com.motta.attendance_service.model.AttendanceDTO;
import com.motta.attendance_service.repository.AttendanceRepository;

import jakarta.transaction.Transactional;

@Service
@Slf4j
public class AttendanceServiceImplementation implements AttendanceService {

	@Autowired
	private AttendanceMapper attendanceMapper;

	@Value("${attendance.id.initialValue}")
	private Integer initialValueOfPrimaryKey;

	@Value("${attendance.working.days.min}")
	private Integer minWorkingDays;

	@Value("${attendance.working.days.max}")
	private Integer maxWorkingDays;

	@Autowired
	private AttendanceRepository repository;

	@Transactional
	@Override
	public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {

		// Check if id already exists
		Optional<Attendance> attendance = repository.findById(attendanceDTO.getId());
		log.info(LOG_MESSAGE_ATTENDANCE_NOT_FOUND + attendanceDTO.getId());

		if (attendance.isPresent()) {
			throw new AttendanceAlreadyExistsException(EXCEPTION_MESSAGE_ATTENDANCE_NOT_FOUND);
		}

		// Convert AttendanceDTO into User JPA Entity
		Attendance newAttendance = attendanceMapper.mapToAttendance(attendanceDTO);
		Attendance savedAttendance = repository.save(newAttendance);
		log.info(LOG_MESSAGE_ATTENDANCE_PERSISTED);
        return attendanceMapper.mapToAttendanceDTO(savedAttendance);
	}

	@Override
	public AttendanceDTO retrieveAttendanceById(Integer id) {
		Attendance attendance = repository.findById(id).orElse(null);
		assert attendance != null;
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		BeanUtils.copyProperties(attendance, attendanceDTO);
		return attendanceDTO;
	}

	@Override
	public List<AttendanceDTO> retrieveAllAttendances() {
		List<Attendance> attendances = repository.findAll();
		return attendances.stream().map(attendanceMapper::mapToAttendanceDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO) {
		// Check if From and To Dates are valid
		validateAttendanceDTO(attendanceDTO);

		Attendance existingAttendance = repository.findById(attendanceDTO.getId()).orElse(new Attendance());
		BeanUtils.copyProperties(existingAttendance, attendanceDTO);
		Attendance updatedScheme = repository.save(existingAttendance);
		log.error(LOG_MESSAGE_ATTENDANCE_UPDATE_FAILED, existingAttendance.getId());
		return attendanceMapper.mapToAttendanceDTO(updatedScheme);
	}

	@Transactional
	@Override
	public void deleteAttendance(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public AttendanceDTO retrieveAttendanceByEmployeeId(int employeeId) {
		List<Attendance> attendances = repository.findAll();
		return attendances.stream().filter(attendance -> attendance.getEmployeeId()==(employeeId))
				.map(attendanceMapper::mapToAttendanceDTO).toList().getFirst();
	}

	@Override
	public void validateAttendanceDTO(AttendanceDTO  attendanceDTO) {

		if (attendanceDTO.getId()==0) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_ATTENDANCE_ID_IS_MANDATORY);
		}

		if (attendanceDTO.getId()<initialValueOfPrimaryKey) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_ATTENDANCE_ID_LESS_THAN_INITIAL_VALUE);
		}

		if (attendanceDTO.getEmployeeId()==0) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_EMPLOYEE_ID_IS_MANDATORY);
		}
		if (attendanceDTO.getNumberOfWorkingDays()==0) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_NUMBER_OF_WORKING_DAYS_IS_MANDATORY);
		}

		if (attendanceDTO.getNumberOfWorkingDays()<minWorkingDays || attendanceDTO.getNumberOfWorkingDays()>maxWorkingDays) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_NUMBER_OF_WORKING_DAYS_IS_INVALID);
		}

		if (attendanceDTO.getCreatedAt()==null) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_CREATED_AT_IS_MANDATORY);
		}

		if (attendanceDTO.getModifiedAt()==null) {
			throw new InvalidAttendanceException(EXCEPTION_MESSAGE_MODIFIED_AT_IS_MANDATORY);
		}
	}


}
