package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_h_surgery", schema = "hospital")
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "surgery_type")
    private Long surgeryType;

    @Column(name = "description")
    private String description;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "admission_id")
    private Long admissionId;

    @Column(name = "patient_reg_no")
    private String patientRegNo;

    @Column(name = "primary_doctor_id")
    private Long primaryDoctorId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "theater_no")
    private Long theaterNo;

    @Column(name = "surgery_date")
    private Date surgeryDate;

    @Column(name = "active")
    private Long active;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Surgery(){

    }

    public Surgery(Long id, Long surgeryType, String description, Long patientId, Long admissionId, String patientRegNo, Long primaryDoctorId, Long doctorId, Long theaterNo, Date surgeryDate, Long active, Long status, Date createdDate, Date updatedDate) {
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
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
