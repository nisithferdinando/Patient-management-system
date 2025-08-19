package com.pms.MediCore.dto.request;

public class DoctorSearchRequest {

    private String firstName;
    private String lastName;
    private Long categoryId;
    private Long wardId;
    private Long active;

    public DoctorSearchRequest(String firstName, String lastName, Long categoryId, Long wardId, Long active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.categoryId = categoryId;
        this.wardId = wardId;
        this.active = active;
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
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
