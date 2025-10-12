package com.pms.MediCore.dto.request;

import java.util.Date;

public class SurgeryRequest {

    private Long id;
    private Long surgeryType;
    private String description;
    private Long patientId;
    private Long admissionId;
    private String patientRegNo;
    private Long primaryDoctorId;
    private Long doctorId;
    private Long theaterNo;
    private Date surgeryDate;
    private Long active;
    private Long status;

    public SurgeryRequest() {

    }

    public SurgeryRequest(Long id, Long surgeryType, String description, Long patientId, Long admissionId, String patientRegNo, Long primaryDoctorId, Long doctorId, Long theaterNo, Date surgeryDate, Long active, Long status) {
        this.id = id;
        this.surgeryType = surgeryType;
        this.description = description;
        this.patientId = patientId;
        this.admissionId = admissionId;
        this.patientRegNo = patientRegNo;
        this.primaryDoctorId = primaryDoctorId;
        this.doctorId = doctorId;
        this.theaterNo = theaterNo;
        this.surgeryDate = surgeryDate;
        this.active = active;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(Long surgeryType) {
        this.surgeryType = surgeryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public String getPatientRegNo() {
        return patientRegNo;
    }

    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
    }

    public Long getPrimaryDoctorId() {
        return primaryDoctorId;
    }

    public void setPrimaryDoctorId(Long primaryDoctorId) {
        this.primaryDoctorId = primaryDoctorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getTheaterNo() {
        return theaterNo;
    }

    public void setTheaterNo(Long theaterNo) {
        this.theaterNo = theaterNo;
    }

    public Date getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
