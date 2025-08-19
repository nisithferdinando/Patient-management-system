package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_h_rooms", schema = "hospital")
public class PatientRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "room_no")
    private Long roomNo;

    @Column(name = "ward_no")
    private Long wardNo;

    @Column(name = "room_type")
    private Long roomType;

    @Column(name = "bed_no")
    private Long bedNo;

    @Column(name = "active")
    private Long active;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "room_status")
    private Long roomStatus;

    public PatientRoom(){

    }

    public PatientRoom(Long roomId, String roomCode, Long roomNo, Long wardNo, Long roomType, Long bedNo, Long active, Long status, Date createdAt, Date updatedOn, Long roomStatus){
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.roomNo = roomNo;
        this.wardNo = wardNo;
        this.roomType = roomType;
        this.bedNo = bedNo;
        this.active = active;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedOn = updatedOn;
        this.roomStatus = roomStatus;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }

    public Long getWardNo() {
        return wardNo;
    }

    public void setWardNo(Long wardNo) {
        this.wardNo = wardNo;
    }

    public Long getRoomType() {
        return roomType;
    }

    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }

    public Long getBedNo() {
        return bedNo;
    }

    public void setBedNo(Long bedNo) {
        this.bedNo = bedNo;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Long roomStatus) {
        this.roomStatus = roomStatus;
    }

}
