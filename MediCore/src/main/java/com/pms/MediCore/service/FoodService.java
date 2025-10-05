package com.pms.MediCore.service;

import com.pms.MediCore.dto.request.FoodDetailsRequest;
import com.pms.MediCore.dto.request.FoodRequest;
import com.pms.MediCore.dto.request.FoodSearchRequest;
import com.pms.MediCore.dto.response.FoodDetailsResponse;
import com.pms.MediCore.dto.response.FoodResponse;
import com.pms.MediCore.dto.response.FoodSearchResponse;
import com.pms.MediCore.dto.response.PatientFoodResponse;

import java.util.List;

public interface FoodService {

    FoodRequest addFood(FoodRequest foodRequest);
    FoodResponse updateFood(Long id, FoodRequest foodRequest);
    List<FoodSearchResponse> searchFood(FoodSearchRequest foodSearchRequest);
    FoodDetailsResponse getFoodDetails(FoodDetailsRequest foodDetailsRequest);
    List<PatientFoodResponse> getAllPatientFood();

}
