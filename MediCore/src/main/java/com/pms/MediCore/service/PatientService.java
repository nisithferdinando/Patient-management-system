package com.pms.MediCore.service;

import com.pms.MediCore.dto.request.PatientRequest;
import com.pms.MediCore.dto.response.PatientDropdownResponse;
import com.pms.MediCore.dto.response.PatientResponse;
import com.pms.MediCore.dto.request.PatientSearchRequest;
import com.pms.MediCore.dto.response.PatientSearchResponse;

import java.util.List;

public interface PatientService {

    PatientRequest addPatient(PatientRequest patientRequest);
    List <PatientResponse> getAllPatients();
    PatientResponse getPatientById(Long id);
     void deletePatient(Long id);
    PatientResponse updatePatient(Long id, PatientRequest patientRequest);
    List<PatientSearchResponse> searchPatient(PatientSearchRequest patientSearchRequest);
    List<PatientDropdownResponse>getPatientDropdown(String regNo);
}
