package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pms_d_doctors", schema = "doctor")
public class Doctor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_id")
    private String docId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "category")
    private Long category;

    @Column(name = "ward")
    private Long ward;

    @Column(name = "age")
    private Long age;

    @Column(name = "gender")
    private Long gender;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Long active;

    @Column(name = "status")
    private Long status;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "state")
    private Long state;

    public Doctor(){

    }
    public Doctor(Long id, String docId, String firstName, String lastName, Long category, Long ward, Long age, Long gender, String email, Date lastModifiedDate, Long active, Long status, Long state ) {
        this.id = id;
        this.docId=docId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.category=category;
        this.ward=ward;
        this.age=age;
        this.gender=gender;
        this.email=email;
        this.status=status;
        this.active=active;
        this.lastModifiedDate=lastModifiedDate;
        this.state=state;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getDocId() {
        return docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
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
    public Long getCategory() {
        return category;
    }
    public void setCategory(Long category) {
        this.category = category;
    }
    public Long getWard() {
        return ward;
    }
    public void setWard(Long ward) {
        this.ward = ward;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public Long getGender() {
        return gender;
    }
    public void setGender(Long gender) {
        this.gender = gender;
    }
    public Long getState(){
        return state;
    }
    public void setState(Long state){
        this.state=state;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getActive(){
        return active;
    }
    public void setActive(Long active){
        this.active=active;
    }
    public Long getStatus() {
        return status;
    }
    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}

