package com.pms.MediCore.dto.request;


import java.util.Date;

public class DoctorRequest {

    private Long id;
    private String docId;
    private String firstName;
    private String lastName;
    private String email;
    private Long category;
    private Long ward;
    private Long age;
    private Long state;
    private Long gender;
    private Long status;
    private Long active;
    private Date lastModifiedDate;

    public DoctorRequest(){

    }
    public DoctorRequest(Long id, String docId, String firstName, String lastName, String email, Long category, Long ward, Long state, Long age, Long gender, Date lastModifiedDate, Long status, Long active ) {
        this.id = id;
        this.docId=docId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.category=category;
        this.ward=ward;
        this.age=age;
        this.gender=gender;
        this.state=state;
        this.status=status;
        this.active=active;
        this.lastModifiedDate=lastModifiedDate;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public Long getState(){
        return state;
    }
    public void setState(Long state) {
        this.state = state;
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

    public Long getActive(){
        return active;
    }
    public void setActive(Long active){
        this.active=active;
    }
    public Long getStatus(){
        return status;
    }
    public void setStatus(Long status){
        this.status=status;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
