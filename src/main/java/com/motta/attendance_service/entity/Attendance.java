package com.motta.attendance_service.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@SequenceGenerator(name = "Custom_Sequence", sequenceName = "custom_sequence", initialValue = 5, allocationSize = 1)
@Data
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Custom_Sequence")
	private int id;

	private int numberOfWorkingDays;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp createdAt;

	@Column(name = "modified_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp modifiedAt;
	private int employeeId;

	public Attendance() {
	}

	public Attendance(int id, int numberOfWorkingDays, Timestamp createdAt, Timestamp modifiedAt, int employeeId) {
		this.id = id;
		this.numberOfWorkingDays = numberOfWorkingDays;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.employeeId = employeeId;
	}
}
