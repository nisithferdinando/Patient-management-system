package com.pms.MediCore.repository;

import com.pms.MediCore.view.DoctorSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorSearchRepository extends JpaRepository<DoctorSearch, Long> {

    @Query(value = "SELECT * FROM doctor.search_doctor_by_filter(:firstName, :lastName, :categoryId, :wardId, :active)", nativeQuery = true)
    List<DoctorSearch> searchDoctorByFilter(String firstName, String lastName, Long categoryId, Long wardId, Long active);
}
