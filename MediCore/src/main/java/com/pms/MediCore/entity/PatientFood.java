package com.pms.MediCore.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_h_meals", schema = "hospital")
public class PatientFood {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealId;

    @Column(name = "meal_code")
    private String mealCode;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "meal_type")
    private Long mealType;

    @Column(name = "meal_charge")
    private Long mealCharge;

    @Column(name = "active")
    private Long active;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_on")
    private Date updatedOn;

    public PatientFood(){

    }
    public PatientFood(Long mealId, String mealCode, String mealName, Long mealType, Long mealCharge, Long active, Long status, Date createdDate, Date updatedOn) {
        this.mealId = mealId;
        this.mealCode=mealCode;
        this.mealName=mealName;
        this.mealType=mealType;
        this.mealCharge=mealCharge;
        this.active=active;
        this.status=status;
        this.createdDate=createdDate;
        this.updatedOn=updatedOn;

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

    public Long getMealType() {
        return mealType;
    }

    public void setMealType(Long mealType) {
        this.mealType = mealType;
    }

    public Long getMealCharge() {
        return mealCharge;
    }

    public void setMealCharge(Long mealCharge) {
        this.mealCharge = mealCharge;
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
