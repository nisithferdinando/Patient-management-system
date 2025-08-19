package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_p_patient_rooms", schema = "patient")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "admission_id")
    private Long admissionId;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_reg_no")
    private String patientRegNo;

    @Column(name = "ward")
    private Long ward;

    @Column(name = "room_category")
    private Long roomCategory;

    @Column(name = "room_type")
    private Long roomType;

    @Column(name = "room_no")
    private Long roomNo;

    @Column(name = "bed_no")
    private String bedNo;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "active")
    private Long active;

    @Column(name = "room_status")
    private Long roomStatus;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public Room(){

    }

    public Room(Long roomId, Long admissionId, Long patientId, String patientRegNo, Long ward, Long roomCategory, Long roomType, Long roomNo, String bedNo, Date startDate, Date endDate, Long active, Long roomStatus, Date createdDate, Date updatedDate){
        this.roomId=roomId;
        this.admissionId=admissionId;
        this.patientId=patientId;
        this.patientRegNo=patientRegNo;
        this.ward=ward;
        this.roomCategory=roomCategory;
        this.roomType=roomType;
        this.roomNo=roomNo;
        this.bedNo=bedNo;
        this.startDate=startDate;
        this.endDate=endDate;
        this.active=active;
        this.roomStatus=roomStatus;
        this.createdDate=createdDate;
        this.updatedDate=updatedDate;
    }

    public Long getRoomId(){
        return roomId;
    }
    public void setRoomId(Long roomId){
        this.roomId=roomId;
    }
    public Long getAdmissionId(){
        return admissionId;
    }
    public void setAdmissionId(Long admissionId){
        this.admissionId=admissionId;
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
    public Long getWard(){
        return ward;
    }
    public void setWard(Long ward){
        this.ward=ward;
    }
    public Long getRoomCategory(){
        return roomCategory;
    }
    public void setRoomCategory(Long roomCategory){
        this.roomCategory=roomCategory;
    }
    public Long getRoomType(){
        return roomType;
    }
    public void setRoomType(Long roomType){
        this.roomType=roomType;
    }
    public Long getRoomNo(){
        return roomNo;
    }
    public void setRoomNo(Long roomNo){
        this.roomNo=roomNo;
    }
    public String getBedNo(){
        return bedNo;
    }
    public void setBedNo(String bedNo){
        this.bedNo=bedNo;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate=startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }
    public Long getActive(){
        return active;
    }
    public void setActive(Long active){
        this.active=active;
    }
    public Long getRoomStatus(){
        return roomStatus;
    }
    public void setRoomStatus(Long roomStatus){
        this.roomStatus=roomStatus;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date createdDate){
        this.createdDate=createdDate;
    }
    public Date getUpdatedDate(){
        return updatedDate;
    }
    public void setUpdatedDate(Date updatedDate){
        this.updatedDate=updatedDate;
    }
}
