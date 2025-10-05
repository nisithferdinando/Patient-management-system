package com.pms.MediCore.dto.response;

public class PatientFoodResponse {

    private Long mealId;
    private String mealName;
    private String mealCode;
    private Long mealType;

    public PatientFoodResponse(){

    }

    public PatientFoodResponse(Long mealId, String mealName, String mealCode, Long mealType) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCode = mealCode;
        this.mealType = mealType;
    }
    public Long getMealId() {
        return mealId;
    }
    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    public String getMealCode() {
        return mealCode;
    }
    public void setMealCode(String mealCode) {
        this.mealCode = mealCode;
    }
    public Long getMealType() {
        return mealType;
    }
    public void setMealType(Long mealType) {
        this.mealType = mealType;
    }
}
