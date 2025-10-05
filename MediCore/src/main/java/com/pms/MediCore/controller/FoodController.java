package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.FoodDetailsRequest;
import com.pms.MediCore.dto.request.FoodRequest;
import com.pms.MediCore.dto.request.FoodSearchRequest;
import com.pms.MediCore.dto.response.FoodDetailsResponse;
import com.pms.MediCore.dto.response.FoodResponse;
import com.pms.MediCore.dto.response.FoodSearchResponse;
import com.pms.MediCore.dto.response.PatientFoodResponse;
import com.pms.MediCore.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService){
        this.foodService=foodService;
    }

    @PostMapping("/add")
    public ResponseEntity<FoodRequest> addFood(@RequestBody FoodRequest foodRequest){
        FoodRequest food=foodService.addFood(foodRequest);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodResponse> updateFood(@RequestBody FoodRequest foodRequest, @PathVariable Long id){
        try{
            FoodResponse food=foodService.updateFood(id, foodRequest);
            return ResponseEntity.status(HttpStatus.OK).body(food);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/search")
    public ResponseEntity<List<FoodSearchResponse>> searchFood(@RequestBody FoodSearchRequest foodSearchRequest){
        List<FoodSearchResponse> food=foodService.searchFood(foodSearchRequest);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @PostMapping("/get")
    public ResponseEntity<FoodDetailsResponse> getFoodDetails(@RequestBody FoodDetailsRequest foodDetailsRequest){
        FoodDetailsResponse food=foodService.getFoodDetails(foodDetailsRequest);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @GetMapping("/food")
    public ResponseEntity<List<PatientFoodResponse>>getAllPatientFood(@RequestParam("mealType") Long mealType){
        List<PatientFoodResponse> patientFoodResponse= foodService.getAllPatientFood(mealType);
        return ResponseEntity.status(HttpStatus.OK).body(patientFoodResponse);
    }
}
