package com.pms.MediCore.repository;

import com.pms.MediCore.view.PatientRegistrationNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionDropdownRepository extends JpaRepository<PatientRegistrationNo, Long> {

    @Query(value = "SELECT * FROM patient.search_by_admission_reg_no(:regNo)", nativeQuery = true)
    List<PatientRegistrationNo> getAdmissionByRegNo(String regNo);
}
