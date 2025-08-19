package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.PatientRequest;
import com.pms.MediCore.dto.response.PatientDropdownResponse;
import com.pms.MediCore.dto.response.PatientResponse;
import com.pms.MediCore.dto.request.PatientSearchRequest;
import com.pms.MediCore.dto.response.PatientSearchResponse;
import com.pms.MediCore.entity.Patient;
import com.pms.MediCore.repository.PatientDropdownRepository;
import com.pms.MediCore.repository.PatientRepository;
import com.pms.MediCore.repository.PatientSearchRepository;
import com.pms.MediCore.service.PatientService;
import com.pms.MediCore.view.PatientDropdown;
import com.pms.MediCore.view.PatientSearch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final PatientSearchRepository patientSearchRepository;
    private final PatientDropdownRepository patientDropdownRepository;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, PatientSearchRepository patientSearchRepository, PatientDropdownRepository patientDropdownRepository) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.patientSearchRepository = patientSearchRepository;
        this.patientDropdownRepository = patientDropdownRepository;
    }

    @Override
    public PatientRequest addPatient(PatientRequest patientRequest) {
        Patient patient=modelMapper.map(patientRequest, Patient.class);
        patient.setCreatedOn(new Date());
        patient.setUpdatedOn(new Date());
        patient.setAdmitStatus(2L);
        Patient newPatient=patientRepository.save(patient);
        return modelMapper.map(newPatient, PatientRequest.class);
    }

    @Override
    public List<PatientResponse> getAllPatients(){
        List<Patient> patients=patientRepository.findAll();
        return patients.stream().map(patient->modelMapper.map(patient, PatientResponse.class)).collect(Collectors.toList());
    }
    @Override
    public PatientResponse getPatientById(Long id){
        Patient patient=patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));
        return modelMapper.map(patient, PatientResponse.class);
    }
    @Override
    public void deletePatient(Long id){
        Patient patient=patientRepository.findByIdAndActive(id, 1L).orElseThrow(()-> new RuntimeException("Patient not found"));
        patient.setActive(2L);
        patient.setUpdatedOn(new Date());
        patientRepository.save(patient);
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {
        Patient patient=patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        patient.setState(patientRequest.getState());
        patient.setGender(patientRequest.getGender());
        patient.setAge(patientRequest.getAge());
        patient.setActive(patientRequest.getActive());
        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        patient.setContactNo(patientRequest.getContactNo());
        patient.setEmail(patientRequest.getEmail());
        patient.setCountry(patientRequest.getCountry());
        patient.setStateName(patientRequest.getStateName());
        patient.setAddressNo(patientRequest.getAddressNo());
        patient.setAddress(patientRequest.getAddress());
        patient.setRegDate(patientRequest.getRegDate());
        patient.setRegTime(patientRequest.getRegTime());
        patient.setRelationName(patientRequest.getRelationName());
        patient.setRelationNo(patientRequest.getRelationNo());
        patient.setEmergencyContactNo(patientRequest.getEmergencyContactNo());
        patient.setRelationState(patientRequest.getRelationState());
        patient.setUpdatedOn(new Date());
        Patient newPatient=patientRepository.save(patient);
        return modelMapper.map(newPatient, PatientResponse.class);
    }

    @Override
    public List<PatientSearchResponse> searchPatient(PatientSearchRequest patientSearchRequest) {
        try {
            List<PatientSearch> patient = patientSearchRepository.searchPatientsByFilters(
                    patientSearchRequest.getFirstName() == null ? "": patientSearchRequest.getFirstName(),
                    patientSearchRequest.getLastName()==null? "" : patientSearchRequest.getLastName(),
                    patientSearchRequest.getRegNo()==null ? "" : patientSearchRequest.getRegNo(),
                    patientSearchRequest.getActive()==null? -2L : patientSearchRequest.getActive()
            );
            return patient.stream().map(p->modelMapper.map(p, PatientSearchResponse.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
   public List<PatientDropdownResponse> getPatientDropdown(String regNo){
        try{
            List<PatientDropdown> patient=patientDropdownRepository.getPatientsByRegNo(regNo);

            return patient.stream().map(p->new PatientDropdownResponse(
                    p.getId(),
                    p.getRegNo(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getState()
            )).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
