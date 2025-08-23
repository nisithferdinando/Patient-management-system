package com.pms.MediCore.repository;

import com.pms.MediCore.view.PatientRoomSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRoomSearchRepository extends JpaRepository<PatientRoomSearch, Long> {

    @Query(value = "SELECT * FROM patient.search_patient_room_by_filters(:patientRegNo, :ward, :roomType, :roomCategory)", nativeQuery = true)
    List<PatientRoomSearch> searchPatientRoomsByFilters(String patientRegNo, Long ward, Long roomType, Long roomCategory);
}
