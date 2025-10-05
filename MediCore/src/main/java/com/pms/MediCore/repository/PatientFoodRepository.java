package com.pms.MediCore.repository;

import com.pms.MediCore.entity.PatientFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientFoodRepository extends JpaRepository<PatientFood, Long> {
    List<PatientFood> findAllByMealTypeAndActive(Long mealType, Long active);
}
