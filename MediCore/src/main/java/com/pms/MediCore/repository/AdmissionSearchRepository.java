package com.pms.MediCore.repository;

import com.pms.MediCore.view.AdmissionSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AdmissionSearchRepository extends JpaRepository<AdmissionSearch, Long> {

    @Query(value = "SELECT * FROM patient.search_admissions_by_filters(:patientRegNo, :admitStatus, :admitCategory, :firstName, :lastName, :createdAt, :active)", nativeQuery = true)
    List<AdmissionSearch> searchAdmissionsByFilters(String patientRegNo, Long admitStatus, Long admitCategory, String firstName, String lastName, Date createdAt, Long active);
}
