package com.pms.MediCore.dto.response;

public class PatientRegistrationNoResponse {
    private Long admissionId;
    private Long PatientId;
    private String patientRegNo;
    private Long state;
    private String stateValue;
    private String firstName;
    private String lastName;

    public PatientRegistrationNoResponse() {
    }

    public PatientRegistrationNoResponse(Long admissionId, Long patientId, String patientRegNo, Long state, String stateValue, String firstName, String lastName) {
        this.admissionId = admissionId;
        PatientId = patientId;
        this.patientRegNo = patientRegNo;
        this.state = state;
        this.stateValue = stateValue;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long patientId) {
        PatientId = patientId;
    }

    public String getPatientRegNo() {
        return patientRegNo;
    }

    public void setPatientRegNO(String patientRegNo) {
        this.patientRegNo = patientRegNo;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
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
}
