package com.pms.MediCore.dto.request;

import java.util.Date;

public class FoodSearchRequest {

    private String patientRegNo;
    private Long roomNo;
    private Long mealType;
    private Date mealDate;
    private Long active;

    public FoodSearchRequest() {

    }
    public FoodSearchRequest(String patientRegNo, Long roomNo, Long mealType, Date mealDate, Long active) {
        this.patientRegNo = patientRegNo;
        this.roomNo = roomNo;
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.active = active;
    }
    public String getPatientRegNo() {
        return patientRegNo;
    }
    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
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
    public Date getMealDate() {
        return mealDate;
    }
    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }

}
