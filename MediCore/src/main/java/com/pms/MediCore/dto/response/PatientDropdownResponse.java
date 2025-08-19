package com.pms.MediCore.dto.response;

public class PatientDropdownResponse {

    private Long patientId;
    private String regNo;
    private String firstName;
    private String lastName;
    private Long state;

    public PatientDropdownResponse( Long patientId, String regNo, String firstName, String lastName, Long state) {
        this.patientId = patientId;
        this.regNo = regNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;

    }
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Long getState() {
        return state;
    }
    public void setState(Long state) {
        this.state = state;
    }
}
