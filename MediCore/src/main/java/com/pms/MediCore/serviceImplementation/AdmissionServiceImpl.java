package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.AdmissionRequest;
import com.pms.MediCore.dto.request.AdmissionSearchRequest;
import com.pms.MediCore.dto.response.AdmissionResponse;
import com.pms.MediCore.dto.response.AdmissionSearchResponse;
import com.pms.MediCore.dto.response.PatientRegistrationNoResponse;
import com.pms.MediCore.entity.Admission;
import com.pms.MediCore.entity.Patient;
import com.pms.MediCore.repository.*;
import com.pms.MediCore.service.AdmissionService;
import com.pms.MediCore.view.AdmissionSearch;
import com.pms.MediCore.view.PatientRegistrationNo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final ModelMapper modelMapper;
    private final AdmissionSearchRepository admissionSearchRepository;
    private final PatientRepository patientRepository;
    private final AdmissionDropdownRepository admissionDropdownRepository;
    private final PatientDropdownRepository patientDropdownRepository;

    public AdmissionServiceImpl(AdmissionRepository admissionRepository, ModelMapper modelMapper, AdmissionSearchRepository admissionSearchRepository, PatientRepository patientRepository, AdmissionDropdownRepository admissionDropdownRepository, PatientDropdownRepository patientDropdownRepository) {
        this.admissionRepository = admissionRepository;
        this.modelMapper = modelMapper;
        this.admissionSearchRepository = admissionSearchRepository;
        this.patientRepository = patientRepository;
        this.admissionDropdownRepository = admissionDropdownRepository;
        this.patientDropdownRepository = patientDropdownRepository;
    }

    @Override
    public AdmissionRequest addAdmission(AdmissionRequest admissionRequest) {
        Patient patient=patientRepository.findById(admissionRequest.getPatientId()).orElseThrow(()->new RuntimeException("Patient Not Found") );
        patient.setAdmitStatus(1L);
        patientRepository.save(patient);
        Admission admission=modelMapper.map(admissionRequest, Admission.class);
        admission.setCreatedAt(new Date());
        admission.setUpdatedOn(new Date());
        Admission savedAdmission=admissionRepository.save(admission);
        System.out.println("admission request" + admissionRequest);
        return modelMapper.map(savedAdmission, AdmissionRequest.class);

    }

    @Override
     public AdmissionResponse updateAdmission(Long id, AdmissionRequest admissionRequest) {
        Admission admission=admissionRepository.findById(id).orElseThrow(()->new RuntimeException("Admission is not found"));
        admission.setAdmissionId(admission.getAdmissionId());
        admission.setPatientId(admissionRequest.getPatientId());
        admission.setFirstName(admissionRequest.getFirstName());
        admission.setLastName(admissionRequest.getLastName());
        admission.setState(admissionRequest.getState());
        admission.setAdmitStatus(admissionRequest.getAdmitStatus());
        admission.setDoctorCategory(admissionRequest.getDoctorCategory());
        admission.setDoctorId(admissionRequest.getDoctorId());
        admission.setDoctor(admissionRequest.getDoctor());
        admission.setAdmitCategory(admissionRequest.getAdmitCategory());
        admission.setDisease(admissionRequest.getDisease());
        admission.setActive(admissionRequest.getActive());
        admission.setUpdatedOn(new Date());

        Admission updatedAdmission=admissionRepository.save(admission);
        return modelMapper.map(updatedAdmission, AdmissionResponse.class);

    }

    @Override
    public List<AdmissionResponse> getAllAdmissions() {
        List<Admission> admissions=admissionRepository.findAll();
        return admissions.stream().map(admission->modelMapper.map(admission, AdmissionResponse.class)).collect(Collectors.toList());
    }

    @Override
    public AdmissionResponse getAdmissionById(Long id){
        Admission admission=admissionRepository.findById(id).orElseThrow(()->new RuntimeException("Admission is not found"));
        return modelMapper.map(admission, AdmissionResponse.class);
    }

    @Override
    public List<AdmissionSearchResponse> searchAdmissions(AdmissionSearchRequest admissionSearchRequest){

        try{
            List<AdmissionSearch> admission=admissionSearchRepository.searchAdmissionsByFilters(
                    admissionSearchRequest.getPatientRegNo()== null? "" : admissionSearchRequest.getPatientRegNo(),
                    admissionSearchRequest.getAdmitStatus()== null? -2L: admissionSearchRequest.getAdmitStatus(),
                    admissionSearchRequest.getAdmitCategory()== null? -2L : admissionSearchRequest.getAdmitCategory(),
                    admissionSearchRequest.getFirstName()==null? "" : admissionSearchRequest.getFirstName(),
                    admissionSearchRequest.getLastName()==null ? "" : admissionSearchRequest.getLastName(),
                    admissionSearchRequest.getCreatedAt()==null? null: admissionSearchRequest.getCreatedAt(),
                    admissionSearchRequest.getActive()==null? -2L : admissionSearchRequest.getActive()

            );


            return admission.stream().map(p->modelMapper.map(p, AdmissionSearchResponse.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PatientRegistrationNoResponse> getPatientRegistrationNo(String regNo){
        try{
            List<PatientRegistrationNo> patientRegistrationNo=admissionDropdownRepository.getAdmissionByRegNo(regNo);
            return patientRegistrationNo.stream().map(p->modelMapper.map(p, PatientRegistrationNoResponse.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
