package com.pms.MediCore.controller;

import com.pms.MediCore.dto.response.WardResponse;
import com.pms.MediCore.service.WardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class WardController {

    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/all")
    ResponseEntity<List<WardResponse>> getAllWards(){
        List<WardResponse> wards=wardService.getAllWards();
        return ResponseEntity.status(HttpStatus.OK).body(wards);
    }
}
