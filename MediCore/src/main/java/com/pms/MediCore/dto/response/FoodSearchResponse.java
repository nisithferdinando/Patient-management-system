package com.pms.MediCore.dto.response;

import java.util.Date;

public class FoodSearchResponse {

    private Long id;
    private Long patientId;
    private String patientRegNo;
    private String patientName;
    private Long roomNo;
    private Long roomId;
    private Long mealType;
    private String mealTypeName;
    private Long mealId;
    private String mealCode;
    private String mealName;
    private Date mealDate;
    private Long active;
    private String activeValue;

    public FoodSearchResponse() {

    }
    public FoodSearchResponse(Long id, Long patientId, String patientRegNo, String patientName, Long roomNo, Long roomId,
                              Long mealType, String mealTypeName, Long mealId, String mealCode, String mealName, Date mealDate,
                              Long active, String activeValue){
        this.id = id;
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.patientName = patientName;
        this.roomNo = roomNo;
        this.roomId = roomId;
        this.mealType = mealType;
        this.mealTypeName = mealTypeName;
        this.mealId = mealId;
        this.mealCode = mealCode;
        this.mealName = mealName;
        this.mealDate = mealDate;
        this.active = active;
        this.activeValue = activeValue;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getMealType() {
        return mealType;
    }

    public void setMealType(Long mealType) {
        this.mealType = mealType;
    }

    public String getMealTypeName() {
        return mealTypeName;
    }

    public void setMealTypeName(String mealTypeName) {
        this.mealTypeName = mealTypeName;
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

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public String getActiveValue() {
        return activeValue;
    }

    public void setActiveValue(String activeValue) {
        this.activeValue = activeValue;
    }
}
