package com.pms.MediCore.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name = "get_patient_food_details_view", schema = "hospital")
public class FoodDetails {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_reg_no")
    private String patientRegNo;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_no")
    private Long roomNo;

    @Column(name = "ward")
    private Long ward;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "meal_type")
    private Long mealType;

    @Column(name = "meal_type_name")
    private String mealTypeName;

    @Column(name = "meal_id")
    private Long mealId;

    @Column(name = "meal_code")
    private String mealCode;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "meal_date")
    private Date mealDate;

    @Column(name = "active")
    private Long active;

    @Column(name = "active_value")
    private String activeValue;

    public FoodDetails() {

    }
    public FoodDetails(Long id, Long patientId, String patientRegNo, Long roomId, Long roomNo, Long ward,
                       String wardName, Long mealType, String mealTypeName, Long mealId, String mealCode,
                       String mealName, Date mealDate, Long active, String activeValue){
        this.id = id;
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.ward = ward;
        this.wardName = wardName;
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

    public Long getWard() {
        return ward;
    }

    public void setWard(Long ward) {
        this.ward = ward;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
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
