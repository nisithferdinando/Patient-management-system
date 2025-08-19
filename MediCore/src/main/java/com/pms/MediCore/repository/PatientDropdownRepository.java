package com.pms.MediCore.repository;

import com.pms.MediCore.view.PatientDropdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDropdownRepository extends JpaRepository<PatientDropdown, Long> {

    @Query(value = "SELECT * FROM patient.search_by_patient_reg_no(:regNo)", nativeQuery = true)

    List<PatientDropdown> getPatientsByRegNo(String regNo);

}
