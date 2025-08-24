package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.FoodRequest;
import com.pms.MediCore.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/add")
    public ResponseEntity<FoodRequest> addFood(@RequestBody FoodRequest foodRequest){
        FoodRequest food=foodService.addFood(foodRequest);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }
}
