package com.pms.MediCore.dto.request;

public class PatientRoomSearchRequest {

    private String patientRegNo;
    private Long ward;
    private Long roomType;
    private Long roomCategory;

    public PatientRoomSearchRequest() {

    }
    public PatientRoomSearchRequest(String patientRegNo, Long ward, Long roomType, Long roomCategory){
        this.patientRegNo=patientRegNo;
        this.ward=ward;
        this.roomType=roomType;
        this.roomCategory=roomCategory;
    }
    public String getPatientRegNo() {
        return patientRegNo;
    }
    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
    }
    public Long getWard() {
        return ward;
    }
    public void setWard(Long ward) {
        this.ward = ward;
    }
    public Long getRoomType() {
        return roomType;
    }
    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }
    public Long getRoomCategory() {
        return roomCategory;
    }
    public void setRoomCategory(Long roomCategory) {
        this.roomCategory = roomCategory;
    }
}
