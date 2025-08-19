package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.NameSearchRequest;
import com.pms.MediCore.dto.response.NameSearchResponse;
import com.pms.MediCore.service.NameSearchService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dropdown")
public class NameSearchController {


    private NameSearchService nameSearchService;

    public NameSearchController(NameSearchService nameSearchService) {
        this.nameSearchService = nameSearchService;
    }

    @GetMapping("/names")
    ResponseEntity<List<NameSearchResponse>> getAllNames(@RequestParam String schema, @RequestParam String tableName, @RequestParam(required = false, defaultValue = "") String search){

        NameSearchRequest request= new NameSearchRequest( schema, tableName, search);
        return ResponseEntity.ok(nameSearchService.getAllNames(request));


    }
}
