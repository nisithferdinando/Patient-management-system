package com.pms.MediCore.dto.response;

public class PatientRoomSearchResponse {
    private Long patientId;
    private String patientRegNo;
    private String fullName;
    private String rooms;
    private String doctor;
    private Long roomStatus;
    private String roomStatusName;

    public PatientRoomSearchResponse() {

    }
    public PatientRoomSearchResponse(Long patientId, String patientRegNo, String fullName, String rooms, String doctor, Long roomStatus, String roomStatusName){
        this.patientId=patientId;
        this.patientRegNo=patientRegNo;
        this.fullName=fullName;
        this.rooms=rooms;
        this.doctor=doctor;
        this.roomStatus=roomStatus;
        this.roomStatusName=roomStatusName;
    }
    public Long getPatientId(){
        return patientId;
    }
    public void setPatientId(Long patientId){
        this.patientId=patientId;
    }
    public String getPatientRegNo(){
        return patientRegNo;
    }
    public void setPatientRegNo(String patientRegNo){
        this.patientRegNo=patientRegNo;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public String getRooms(){
        return rooms;
    }
    public void setRooms(String rooms){
        this.rooms=rooms;
    }
    public String getDoctor(){
        return doctor;
    }
    public void setDoctor(String doctor){
        this.doctor=doctor;
    }
    public Long getRoomStatus(){
        return roomStatus;
    }
    public void setRoomStatus(Long roomStatus){
        this.roomStatus=roomStatus;
    }
    public String getRoomStatusName(){
        return roomStatusName;
    }
    public void setRoomStatusName(String roomStatusName){
        this.roomStatusName=roomStatusName;
    }
}
