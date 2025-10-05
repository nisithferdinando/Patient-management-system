package com.pms.MediCore.repository;

import com.pms.MediCore.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByPatientIdAndActive(Long patientId, Long active);
    List<Room> findByRoomStatus(Long RoomStatus);
}
