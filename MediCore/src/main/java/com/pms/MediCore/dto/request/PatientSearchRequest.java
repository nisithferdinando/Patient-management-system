package com.pms.MediCore.dto.request;

public class PatientSearchRequest {

    private String firstName;
    private String lastName;
    private String regNo;
    private Long active;


    public PatientSearchRequest(String firstName, String lastName, String regNo, Long active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.regNo = regNo;
        this.active = active;
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
    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }


}
