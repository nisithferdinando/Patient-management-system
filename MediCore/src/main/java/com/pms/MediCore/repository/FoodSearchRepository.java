package com.pms.MediCore.repository;

import com.pms.MediCore.view.FoodSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FoodSearchRepository extends JpaRepository<FoodSearch, Long> {

    @Query(value = "SELECT * FROM hospital.search_patient_food_by_filters(:patientRegNo, :roomNo, :mealType, :mealDate, :active)", nativeQuery = true)
    List<FoodSearch> searchPatientFood(String patientRegNo, Long roomNo, Long mealType, Date mealDate, Long active);
}
