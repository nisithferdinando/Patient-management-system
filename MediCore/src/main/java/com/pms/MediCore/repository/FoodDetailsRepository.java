package com.pms.MediCore.repository;

import com.pms.MediCore.view.FoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Long> {

    @Query(value = "SELECT * FROM hospital.get_patient_food_details(:id, :patientId)", nativeQuery = true)
    FoodDetails findPatientFoodDetails(Long id, Long patientId);
}
