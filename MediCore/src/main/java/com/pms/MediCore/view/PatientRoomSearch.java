package com.pms.MediCore.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class PatientRoomSearch {

    @Id
    private Long patientId;
    private String patientRegNo;
    private String fullName;
    private String rooms;
    private String doctor;
    private Long active;
    private Long roomStatus;
    private String roomStatusName;

    public PatientRoomSearch() {

    }
    public PatientRoomSearch(Long patientId, String patientRegNo, String fullName, String rooms, String doctor, Long active,Long roomStatus, String roomStatusName) {
        this.patientId = patientId;
        this.patientRegNo = patientRegNo;
        this.fullName = fullName;
        this.rooms = rooms;
        this.doctor = doctor;
        this.active = active;
        this.roomStatus=roomStatus;
        this.roomStatusName=roomStatusName;
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
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRooms() {
        return rooms;
    }
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
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
    public String getRoomStatusName() {
        return roomStatusName;
    }
    public void setRoomStatusName(String roomStatusName) {
        this.roomStatusName = roomStatusName;
    }

}
