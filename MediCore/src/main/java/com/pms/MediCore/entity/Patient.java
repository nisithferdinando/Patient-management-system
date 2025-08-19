package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "pms_p_patient", schema = "patient")
public class Patient {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "state")
    private Long state;

    @Column(name = "gender")
    private Long gender;

    @Column(name = "age")
    private Long age;

    @Column(name = "active")
    private Long active;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "address_no")
    private String addressNo;

    @Column(name = "address")
    private String address;

    @Column(name = "relation_name")
    private String relationName;

    @Column(name = "relation_contact_no")
    private String relationNo;

    @Column(name = "emergency_contact_no")
    private String emergencyContactNo;

    @Column(name = "relation_state")
    private Long relationState;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "reg_time")
    private Time regTime;

    @Column(name = "admit_status")
    private Long admitStatus;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    public Patient(){

    }

    public Patient(Long id, String regNo, String firstName, String lastName, Long state, Long gender, Long age,
                   Long active, Date dateOfBirth, String contactNo, String email, String country, String stateName,
                   String addressNo, String address, String relationName, String relationNo, String emergencyContactNo, Long relationState, Date regDate, Time regTime, Long admitStatus, Date createdOn, Date updatedOn) {
        this.id = id;
        this.regNo = regNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.gender = gender;
        this.age = age;
        this.active = active;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.email = email;
        this.country = country;
        this.stateName = stateName;
        this.addressNo = addressNo;
        this.address = address;
        this.relationName = relationName;
        this.relationNo = relationNo;
        this.emergencyContactNo = emergencyContactNo;
        this.relationState = relationState;
        this.regDate = regDate;
        this.regTime = regTime;
        this.admitStatus = admitStatus;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Time getRegTime() {
        return regTime;
    }

    public void setRegTime(Time regTime) {
        this.regTime = regTime;
    }
    public String getRelationName(){
        return relationName;
    }
    public void setRelationName(String relationName){
        this.relationName = relationName;
    }
    public String getRelationNo(){
        return relationNo;
    }
    public void setRelationNo(String relationNo){
        this.relationNo = relationNo;
    }
    public String getEmergencyContactNo(){
        return emergencyContactNo;
    }
    public void setEmergencyContactNo(String emergencyContactNo){
        this.emergencyContactNo = emergencyContactNo;
    }
    public Long getRelationState(){
        return relationState;
    }
    public void setRelationState(Long relationState){
        this.relationState = relationState;
    }

    public Long getAdmitStatus(){
        return admitStatus;
    }
    public void setAdmitStatus(Long admitStatus){
        this.admitStatus = admitStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
