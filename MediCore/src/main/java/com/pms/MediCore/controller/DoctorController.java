package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.DoctorRequest;
import com.pms.MediCore.dto.response.DoctorResponse;
import com.pms.MediCore.dto.request.DoctorSearchRequest;
import com.pms.MediCore.dto.response.DoctorSearchResponse;
import com.pms.MediCore.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService=doctorService;
    }

    @PostMapping("/add")
    ResponseEntity<DoctorRequest> addDoctor( @RequestBody DoctorRequest doctorRequest){
        try {
            System.out.println("Received doctor request: " + doctorRequest.toString());
            DoctorRequest newDoctor = doctorService.addDoctor(doctorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body((newDoctor));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<DoctorResponse>>getAllDoctors(){
        List<DoctorResponse> doctors=doctorService.getAllDoctors();
        if(doctors.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }

        return ResponseEntity.status(HttpStatus.OK).body(doctors);

    }

    @GetMapping("/{id}")
    ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id){
        try{
            DoctorResponse doctor=doctorService.getDoctorById(id);
            return ResponseEntity.status((HttpStatus.OK)).body(doctor);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<DoctorResponse> updateDoctor(@RequestBody DoctorRequest doctorRequest, @PathVariable Long id){
        DoctorResponse doctor=doctorService.updateDoctor(doctorRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }

    @PutMapping("/delete/{id}")
    ResponseEntity<String>deleteDoctor(@PathVariable Long id){
       doctorService.deleteDoctor(id);
       return ResponseEntity.ok("Doctor deleted successfully");
    }

    @PostMapping("/search")
    ResponseEntity<List<DoctorSearchResponse>> searchDoctor(@RequestBody DoctorSearchRequest doctorSearchRequest){
       List<DoctorSearchResponse> doctor=doctorService.searchDoctor(doctorSearchRequest);
       return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }

    @GetMapping("/get/doctors")
    ResponseEntity<List<DoctorResponse>> getDoctorsByCategory(@RequestParam Long category){
        List<DoctorResponse> doctor=doctorService.findByDoctorByCategory(category, 1L);
        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }
}
