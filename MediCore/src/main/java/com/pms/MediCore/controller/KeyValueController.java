package com.pms.MediCore.controller;

import com.pms.MediCore.dto.response.KeyValueResponse;
import com.pms.MediCore.service.KeyValueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/key")
public class KeyValueController {

    private final KeyValueService keyValueService;
    public KeyValueController(KeyValueService keyValueService) {
        this.keyValueService = keyValueService;
    }

    @GetMapping("/key")
    ResponseEntity<List<KeyValueResponse>> getKeyValue(@RequestParam("key") String key){
    List<KeyValueResponse> keyValue= keyValueService.getValueByKey(key);
    return  ResponseEntity.status(HttpStatus.OK).body(keyValue);

    }
}
