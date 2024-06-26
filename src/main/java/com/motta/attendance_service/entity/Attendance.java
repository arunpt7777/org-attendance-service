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

@Entity
@SequenceGenerator(name = "Custom_Sequence", sequenceName = "custom_sequence", initialValue = 5, allocationSize = 1)
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Custom_Sequence")
	private Integer id;

	@Min(value = 1, message = "No. of working days must be greater than 1")
	@Max(value = 30, message = "No. of working days must be lesser than 30")
	private Integer numberOfWorkingDays;

	@Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp createdAt;

	@Column(name = "modified_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Timestamp modifiedAt;

	private Integer employeeId;

	public Attendance() {
	}

	public Attendance(Integer id, @NotEmpty(message = "Currency must not be empty") Integer numberOfWorkingDays,
			Timestamp createdAt, Timestamp modifiedAt, Integer employeeId) {
		super();
		this.id = id;
		this.numberOfWorkingDays = numberOfWorkingDays;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.employeeId = employeeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfWorkingDays() {
		return numberOfWorkingDays;
	}

	public void setNumberOfWorkingDays(Integer numberOfWorkingDays) {
		this.numberOfWorkingDays = numberOfWorkingDays;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", numberOfWorkingDays=" + numberOfWorkingDays + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", employeeId=" + employeeId + "]";
	}

}
