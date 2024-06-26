package com.motta.attendance_service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AttendanceConfiguration {

	public RestTemplate getAttendanceRestTemplate() {
		return new RestTemplate();
	}
}
