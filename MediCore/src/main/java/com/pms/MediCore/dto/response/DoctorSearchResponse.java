package com.pms.MediCore.dto.response;

public class DoctorSearchResponse {

    private Long id;
    private String docId;
    private String firstName;
    private String lastName;
    private String fullName;
    private Long statusId;
    private String statusName;
    private Long categoryId;
    private String categoryName;
    private Long wardId;
    private String wardName;
    private Long active;

    public DoctorSearchResponse(){

    }
    public DoctorSearchResponse(Long id, String docId, String firstName, String lastName, String fullName, Long statusId, String statusName, Long categoryId, String categoryName, Long wardId, String wardName, Long active) {
        this.id = id;
        this.docId = docId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.statusId = statusId;
        this.statusName = statusName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.active = active;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStatusId(){
        return statusId;
    }

    public void setStatusId(Long statusId){
        this.statusId = statusId;
    }

    public String getStatusName(){
        return statusName;
    }

    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }
}