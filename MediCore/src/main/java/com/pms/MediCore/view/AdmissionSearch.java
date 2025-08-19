package com.pms.MediCore.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
public class AdmissionSearch {
    @Id
    private Long admissionId;
    private String regNo;
    private String stateName;
    private String fullName;
    private Long admitCategory;
    private String admitCategoryName;
    private Long admitStatus;
    private String admitStatusName;
    private Date createdAt;
    private Long  active;
    private String activeName;

    public AdmissionSearch() {

    }
    public AdmissionSearch(Long admissionId, String regNo, String stateName, String fullName,
                           Long admitCategory, String admitCategoryName, Long admitStatus,
                           String admitStatusName, Date createdAt, Long active, String activeName){
        this.admissionId = admissionId;
        this.regNo = regNo;
        this.stateName = stateName;
        this.fullName = fullName;
        this.admitCategory = admitCategory;
        this.admitCategoryName = admitCategoryName;
        this.admitStatus = admitStatus;
        this.admitStatusName = admitStatusName;
        this.createdAt = createdAt;
        this.active = active;
        this.activeName = activeName;

    }
    public Long getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
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

    public Long getAdmitCategory() {
        return admitCategory;
    }

    public void setAdmitCategory(Long admitCategory) {
        this.admitCategory = admitCategory;
    }

    public String getAdmitCategoryName() {
        return admitCategoryName;
    }

    public void setAdmitCategoryName(String admitCategoryName) {
        this.admitCategoryName = admitCategoryName;
    }

    public Long getAdmitStatus() {
        return admitStatus;
    }

    public void setAdmitStatus(Long admitStatus) {
        this.admitStatus = admitStatus;
    }

    public String getAdmitStatusName() {
        return admitStatusName;
    }

    public void setAdmitStatusName(String admitStatusName) {
        this.admitStatusName = admitStatusName;
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

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }
}
