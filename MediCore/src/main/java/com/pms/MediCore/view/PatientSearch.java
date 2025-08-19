package com.pms.MediCore.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

import java.sql.Date;

@Entity
@Immutable
public class PatientSearch {
    @Id
    private Long id;
    private String regNo;
    private String stateName;
    private String fullName;
    private String genderName;
    private String relativeName;
    private Date regDate;
    private String contactNo;
    private String activeStatus;

    public PatientSearch() {

    }

    public PatientSearch( Long id, String regNo, String stateName, String fullName, String genderName, String relativeName, Date regDate, String contactNo, String activeStatus ) {
        this.id = id;
        this.regNo = regNo;
        this.stateName = stateName;
        this.fullName = fullName;
        this.genderName = genderName;
        this.relativeName = relativeName;
        this.regDate = regDate;
        this.contactNo = contactNo;
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
    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGenderName() {
        return genderName;
    }
    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
    public String getRelativeName() {
        return relativeName;
    }
    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getActiveStatus() {
        return activeStatus;
    }
    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

}
