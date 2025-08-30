package com.pms.MediCore.dto.request;

import java.util.Date;

public class RoomRequest {

    private Long roomId;
    private Long admissionId;
    private Long patientId;
    private String patientRegNo;
    private Long ward;
    private Long roomCategory;
    private Long roomType;
    private Long roomNo;
    private Long room;
    private String bedNo;
    private Date startDate;
    private Date endDate;
    private Long active;
    private Long roomStatus;
    private Date createdDate;
    private Date updatedDate;

    public RoomRequest() {

    }
    public RoomRequest(Long roomId, Long admissionId, Long patientId, Long ward, String patientRegNo, Long roomCategory, Long roomType, Long roomNo, Long room, String bedNo, Date startDate, Date endDate, Long active, Long roomStatus, Date createdDate, Date updatedDate) {
        this.roomId = roomId;
        this.admissionId = admissionId;
        this.patientId = patientId;
        this.ward = ward;
        this.patientRegNo = patientRegNo;
        this.roomCategory = roomCategory;
        this.roomType = roomType;
        this.roomNo = roomNo;
        this.room = room;
        this.bedNo = bedNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.roomStatus = roomStatus;
        this.createdDate=createdDate;
        this.updatedDate=updatedDate;
    }
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public Long getAdmissionId() {
        return admissionId;
    }
    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public Long getWard() {
        return ward;
    }
    public void setWard(Long ward) {
        this.ward = ward;
    }
    public String getPatientRegNo() {
        return patientRegNo;
    }
    public void setPatientRegNo(String patientRegNo) {
        this.patientRegNo = patientRegNo;
    }
    public Long getRoomCategory() {
        return roomCategory;
    }
    public void setRoomCategory(Long roomCategory) {
        this.roomCategory = roomCategory;
    }
    public Long getRoomType() {
        return roomType;
    }
    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }
    public Long getRoomNo() {
        return roomNo;
    }
    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }
    public Long getRoom() {
        return room;
    }
    public void setRoom(Long room) {
        this.room = room;
    }
    public String getBedNo() {
        return bedNo;
    }
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }
    public Long getRoomStatus() {
        return roomStatus;
    }
    public void setRoomStatus(Long roomStatus) {
        this.roomStatus = roomStatus;
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

}
