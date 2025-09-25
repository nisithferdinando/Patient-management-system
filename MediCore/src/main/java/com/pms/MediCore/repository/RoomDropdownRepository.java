package com.pms.MediCore.repository;

import com.pms.MediCore.view.RoomDropdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDropdownRepository extends JpaRepository<RoomDropdown, Long> {

    @Query(value = "SELECT * FROM hospital.get_rooms_by_filters(:roomCategory, :roomType, :ward)", nativeQuery = true)
    List<RoomDropdown> getRoomsByCategory(Long roomCategory, Long roomType, Long ward);
}
