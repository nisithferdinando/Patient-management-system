package com.pms.MediCore.dto.response;

public class PatientSearchResponse {

    private Long id;
    private String regNo;
    private String fullName;
    private String gender;
    private String contactNo;
    private String relativeName;
    private String activeStatus;

    public PatientSearchResponse() {

    }

    public PatientSearchResponse(Long id, String regNo, String fullName, String gender, String contactNo, String relativeName, String activeStatus) {
        this.id = id;
        this.regNo = regNo;
        this.fullName = fullName;
        this.gender = gender;
        this.contactNo = contactNo;
        this.relativeName = relativeName;
        this.activeStatus = activeStatus;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getRelativeName() {
        return relativeName;
    }
    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }
    public String getActiveStatus() {
        return activeStatus;
    }
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

}
