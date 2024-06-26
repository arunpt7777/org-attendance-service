package com.motta.attendance_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motta.attendance_service.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}
