package com.pms.MediCore.dto.request;

import java.util.Date;

public class AdmissionSearchRequest {

    private String patientRegNo;
    private Long admitStatus;
    private Long admitCategory;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private Long active;

    public AdmissionSearchRequest() {

    }
    public AdmissionSearchRequest(String patientRegNo, Long admitStatus, Long admitCategory, String firstName, String lastName, Date createdAt, Long active) {
        this.patientRegNo = patientRegNo;
        this.admitStatus = admitStatus;
        this.admitCategory = admitCategory;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.active = active;
    }

    public String getPatientRegNo() {
        return patientRegNo;
    }
    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
    }
    public Long getAdmitStatus() {
        return admitStatus;
    }
    public void setAdmitStatus(Long admitStatus) {
        this.admitStatus = admitStatus;
    }
    public Long getAdmitCategory() {
        return admitCategory;
    }
    public void setAdmitCategory(Long admitCategory) {
        this.admitCategory = admitCategory;
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
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }
}

