package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_p_admission", schema = "patient")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_reg_no")
    private String patientRegNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "state")
    private Long state;

    @Column(name = "admit_status")
    private Long admitStatus;

    @Column(name = "doctor")
    private Long doctor;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "admit_category")
    private Long admitCategory;

    @Column(name = "disease")
    private String disease;

    @Column(name = "active")
    private Long active;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "doctor_category")
    private Long doctorCategory;

    public Admission() {
    }

    public Admission(Long admissionId, Long patientId, String patientRegNo, String firstName, String lastName,
                     Long state, Long admitStatus, Long doctor, String doctorId, Long admitCategory, String disease,
                     Long active, Date createdAt, Date updatedOn, Long doctorCategory) {
        this.admissionId = admissionId;
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.admitStatus = admitStatus;
        this.doctor = doctor;
        this.doctorId = doctorId;
        this.admitCategory = admitCategory;
        this.disease = disease;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedOn = updatedOn;
        this.doctorCategory = doctorCategory;
    }
    public Long getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientRegNo() {
        return patientRegNo;
    }

    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
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
    public Long getAdmitStatus() {
        return admitStatus;
    }
    public void setAdmitStatus(Long admitStatus) {
        this.admitStatus = admitStatus;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Long getAdmitCategory() {
        return admitCategory;
    }

    public void setAdmitCategory(Long admitCategory) {
        this.admitCategory = admitCategory;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getDoctorCategory(){
        return doctorCategory;
    }
    public void setDoctorCategory(Long doctorCategory){
        this.doctorCategory = doctorCategory;
    }


}
