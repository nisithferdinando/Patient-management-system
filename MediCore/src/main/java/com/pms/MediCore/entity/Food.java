package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_h_patient_meals", schema = "hospital")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_reg_no")
    private String patientRegNo;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_no")
    private Long roomNo;

    @Column(name = "meal_type")
    private Long mealType;

    @Column(name = "meal_id")
    private Long mealId;

    @Column(name = "meal_code")
    private String mealCode;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "meal_date")
    private Date mealDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "active")
    private Long active;

    @Column(name = "status")
    private Long status;

    public Food(){

    }
    public Food(Long id, Long patientId, String patientRegNo, Long roomId, Long roomNo, Long mealType, Long mealId, String mealCode, String mealName, Date mealDate, Date createdDate, Date updatedDate, Long active, Long status) {
        this.id = id;
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.mealType = mealType;
        this.mealId = mealId;
        this.mealCode = mealCode;
        this.mealName = mealName;
        this.mealDate = mealDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public Long getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
    public Long getMealType() {
        return mealType;
    }
    public void setMealType(Long mealType) {
        this.mealType = mealType;
    }
    public Long getMealId() {
        return mealId;
    }
    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }
    public String getMealCode() {
        return mealCode;
    }
    public void setMealCode(String mealCode) {
        this.mealCode = mealCode;
    }
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Date getMealDate() {
        return mealDate;
    }
    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
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
