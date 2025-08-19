package com.pms.MediCore.repository;

import com.pms.MediCore.view.PatientSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientSearchRepository extends JpaRepository<PatientSearch, Long> {

    @Query(value = "SELECT * FROM patient.search_patients_by_filters(:firstName, :lastName, :regNo, :active)", nativeQuery = true)
    List<PatientSearch> searchPatientsByFilters(String firstName, String lastName, String regNo, Long active);
}
