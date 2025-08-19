package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.AdmissionRequest;
import com.pms.MediCore.dto.request.AdmissionSearchRequest;
import com.pms.MediCore.dto.response.AdmissionResponse;
import com.pms.MediCore.dto.response.AdmissionSearchResponse;
import com.pms.MediCore.service.AdmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    private AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping("/add")
    ResponseEntity<AdmissionRequest> addAdmission(@RequestBody AdmissionRequest admissionRequest) {
        try{
            AdmissionRequest request=admissionService.addAdmission(admissionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        }

        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<AdmissionResponse>> getAllAdmission() {
        List<AdmissionResponse> admissionResponse=admissionService.getAllAdmissions();
        if(admissionResponse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(admissionResponse);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<AdmissionResponse> updateAdmission(@PathVariable Long id, @RequestBody AdmissionRequest admissionRequest) {
        AdmissionResponse response=admissionService.updateAdmission(id, admissionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<AdmissionResponse> getAdmissionById(@PathVariable Long id){
        AdmissionResponse response=admissionService.getAdmissionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/search")
    ResponseEntity<List<AdmissionSearchResponse>>searchAdmission(@RequestBody AdmissionSearchRequest admissionSearchRequest){
        List<AdmissionSearchResponse> admission=admissionService.searchAdmissions(admissionSearchRequest);
        return ResponseEntity.status(HttpStatus.OK).body(admission);
    }

}
