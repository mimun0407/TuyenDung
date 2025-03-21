package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityReasonHiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonHiringRepo extends JpaRepository<EntityReasonHiring, Long>, JpaSpecificationExecutor<EntityReasonHiring> {
    boolean existsByCode(String code);
}