package com.pms.MediCore.dto.request;

public class FoodDetailsRequest {

    private Long id;
    private Long patientId;

    public FoodDetailsRequest() {

    }
    public FoodDetailsRequest(Long id, Long patientId) {
        this.id = id;
        this.patientId = patientId;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getPatientId(){
        return patientId;
    }
    public void setPatientId(Long patientId){
        this.patientId = patientId;
    }
}
