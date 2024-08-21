package com.motta.attendance_service.util;

public class AttendanceConstants {
    public static final String LOG_MESSAGE_ATTENDANCE_NOT_FOUND = "Attendance id not found. Please enter different id";
    public static final String LOG_MESSAGE_ATTENDANCE_PERSISTED = "Attendance persisted";
    public static final String LOG_MESSAGE_ATTENDANCE_UPDATE_FAILED = "Updating scheme id = {} has failed.";
    public static final String LOG_FETCHING_ASSOCIATIONS_FAILED = "Failed fetching associations for scheme Id.";

    public static final String EXCEPTION_MESSAGE_ATTENDANCE_NOT_FOUND = "Attendance id = {} already Exists!";
    public static final String EXCEPTION_MESSAGE_INVALID_FROM_DATE = "From Date should be less than To Date";
    public static final String EXCEPTION_MESSAGE_ATTENDANCE_ID_IS_MANDATORY = "Attendance Id is mandatory";
    public static final String EXCEPTION_MESSAGE_ATTENDANCE_NAME_IS_MANDATORY = "Attendance Name is mandatory";
    public static final String EXCEPTION_MESSAGE_ATTENDANCE_ID_LESS_THAN_INITIAL_VALUE = "Attendance Id must not be less than the initial value of: ";
    public static final String EXCEPTION_MESSAGE_TO_DATE_IS_MANDATORY = "Valid To Date is mandatory";
    public static final String EXCEPTION_MESSAGE_ATTENDANCE_TYPE_IS_MANDATORY = "Attendance Type is mandatory";
    public static final String EXCEPTION_MESSAGE_NUMBER_OF_WORKING_DAYS_IS_MANDATORY = "Number of working days is mandatory";
    public static final String EXCEPTION_MESSAGE_NUMBER_OF_WORKING_DAYS_IS_INVALID = "Number of working days must be between {} and {}";
    public static final String EXCEPTION_MESSAGE_EMPLOYEE_ID_IS_MANDATORY = "Employee Id is mandatory";
    public static final String EXCEPTION_MESSAGE_CREATED_AT_IS_MANDATORY = "Created At is mandatory";
    public static final String EXCEPTION_MESSAGE_MODIFIED_AT_IS_MANDATORY = "Modified At is mandatory";

    public static final String EXCEPTION_MESSAGE_ATTENDANCE_AMOUNT_IS_MANDATORY = "Attendance Amount is mandatory";
    public static final String EXCEPTION_MESSAGE_SHARE_IS_MANDATORY = "Share  is mandatory";
    public static final String EXCEPTION_MESSAGE_COMMISSION_IS_MANDATORY = "Commission  is mandatory";
    public static final String EXCEPTION_MESSAGE_ASSOCIATIONS_NOT_FOUND = "Associations not found";

    public static final String URL_GET_ASSOCIATIONS_BY_ATTENDANCE_ID = "http://localhost:8900/getassociationsbyschemeid/";


}
