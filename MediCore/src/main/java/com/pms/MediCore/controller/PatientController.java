package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.PatientRequest;
import com.pms.MediCore.dto.response.PatientDropdownResponse;
import com.pms.MediCore.dto.response.PatientResponse;
import com.pms.MediCore.dto.request.PatientSearchRequest;
import com.pms.MediCore.dto.response.PatientSearchResponse;
import com.pms.MediCore.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    ResponseEntity<PatientRequest> addPatient(@RequestBody PatientRequest patientRequest) {
        PatientRequest patient=patientService.addPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @GetMapping("/all")
    ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> patients=patientService.getAllPatients();
        if(patients.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    ResponseEntity<PatientResponse> getPatientById(@PathVariable("id") Long id){
        PatientResponse patient=patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }
    @PutMapping("/delete/{id}")
    ResponseEntity<String> deletePatient(@PathVariable("id") Long id){
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    ResponseEntity<PatientResponse> updatePatient(@PathVariable("id") Long id, @RequestBody PatientRequest patientRequest){
        PatientResponse patient=patientService.updatePatient(id, patientRequest);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @PostMapping("/search")
    ResponseEntity<List<PatientSearchResponse>> searchPatient(@RequestBody PatientSearchRequest patientSearchRequest){
        List<PatientSearchResponse> patient=patientService.searchPatient(patientSearchRequest);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @GetMapping("/dropdown/search")
    ResponseEntity<List<PatientDropdownResponse>>getPatientDropdown(@RequestParam String regNo){
        List<PatientDropdownResponse> patient=patientService.getPatientDropdown(regNo);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

}
