package com.pms.MediCore.service;


import com.pms.MediCore.dto.request.DoctorRequest;
import com.pms.MediCore.dto.response.DoctorResponse;
import com.pms.MediCore.dto.request.DoctorSearchRequest;
import com.pms.MediCore.dto.response.DoctorSearchResponse;

import java.util.List;

public interface DoctorService {

   DoctorRequest addDoctor(DoctorRequest doctorRequest);
   List<DoctorResponse> getAllDoctors();
   DoctorResponse getDoctorById(Long id);

   DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long id);
   void deleteDoctor(Long id);
   List<DoctorSearchResponse> searchDoctor(DoctorSearchRequest doctorSearchRequest);
   List<DoctorResponse> findByDoctorByCategory(Long category, Long active);
}
