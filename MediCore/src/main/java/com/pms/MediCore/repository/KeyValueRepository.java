package com.pms.MediCore.repository;

import com.pms.MediCore.entity.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyValueRepository extends JpaRepository<KeyValue, Long> {
    List<KeyValue>findByKeyValue(String keyValue);
}
