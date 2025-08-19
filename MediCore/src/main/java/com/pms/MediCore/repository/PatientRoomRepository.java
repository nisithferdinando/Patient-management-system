package com.pms.MediCore.repository;

import com.pms.MediCore.entity.PatientRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRoomRepository extends JpaRepository<PatientRoom, Long> {

    Optional<PatientRoom>findByRoomNo(Long roomNo);
}
