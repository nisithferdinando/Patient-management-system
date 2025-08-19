package com.pms.MediCore.repository;

import com.pms.MediCore.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor>findByActive(Long active);
    Optional<Doctor> findByIdAndActive(Long id, Long active);
    List<Doctor> findByCategoryAndActive(Long category, Long active);


}
