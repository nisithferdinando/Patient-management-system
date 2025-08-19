package com.pms.MediCore.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class PatientDropdown {
    @Id
    private Long id;
    private String regNo;
    private String firstName;
    private String lastName;
    private Long state;
    private Long active;

   public PatientDropdown() {

   }
   public PatientDropdown(Long id, String regNo, String firstName, String lastName, Long state, Long active) {
       this.id = id;
       this.regNo = regNo;
       this.firstName = firstName;
       this.lastName = lastName;
       this.state = state;
       this.active = active;
   }
   public Long getId() {
       return id;

   }
   public void setId(Long id) {
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
   public Long getActive() {
       return active;
   }
   public void setActive(Long active) {
       this.active = active;
   }

}
