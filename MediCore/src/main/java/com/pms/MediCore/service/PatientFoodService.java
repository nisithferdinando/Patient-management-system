package com.pms.MediCore.service;

import com.pms.MediCore.dto.response.PatientFoodResponse;

import java.util.List;

public interface PatientFoodService {

    List<PatientFoodResponse> getAllPatientFood(Long mealType);
}
