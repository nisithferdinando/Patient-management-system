package com.pms.MediCore.service;

import com.pms.MediCore.dto.request.AdmissionRequest;
import com.pms.MediCore.dto.request.AdmissionSearchRequest;
import com.pms.MediCore.dto.response.AdmissionResponse;
import com.pms.MediCore.dto.response.AdmissionSearchResponse;

import java.util.List;

public interface AdmissionService {

    AdmissionRequest addAdmission(AdmissionRequest admissionRequest);
    AdmissionResponse updateAdmission(Long id, AdmissionRequest admissionRequest);
    AdmissionResponse getAdmissionById(Long id);
    List<AdmissionResponse> getAllAdmissions();
    List<AdmissionSearchResponse>searchAdmissions(AdmissionSearchRequest admissionSearchRequest);
}
