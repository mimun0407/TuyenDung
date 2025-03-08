package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepo extends JpaRepository<EntityApplicationForm, Long>, JpaSpecificationExecutor<EntityApplicationForm> {
    boolean existsByCode(String code);
}