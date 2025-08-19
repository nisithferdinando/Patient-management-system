package com.pms.MediCore.repository;

import com.pms.MediCore.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByActive(Long active);
    Optional<Patient> findByIdAndActive(Long id, Long active);
}
