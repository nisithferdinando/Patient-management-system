package com.pms.MediCore.repository;

import com.pms.MediCore.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findAllByActiveOrderByWardIdAsc(Long active);
}
