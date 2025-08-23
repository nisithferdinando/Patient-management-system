package com.pms.MediCore.dto.request;

public class FoodRequest {

    private Long id;
    private Long patientId;
    private String patientRegNo;
    private Long roomId;
    private Long roomNo;
    private Long mealType;
    private Long mealId;
    private String mealCode;
    private String mealName;
    private Long active;
    private Long status;

    public FoodRequest() {

    }
    public FoodRequest(Long id, Long patientId, String patientRegNo, Long roomId, Long roomNo,
                       Long mealType, Long mealId, String mealCode, String mealName,
                       Long active, Long status ){
        this.id = id;
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.mealType = mealType;
        this.mealCode = mealCode;
        this.mealName = mealName;
        this.active = active;
        this.status = status;

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
