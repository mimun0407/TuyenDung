package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityEmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityEmailModelRepo extends JpaRepository<EntityEmailModel, Long>, JpaSpecificationExecutor<EntityEmailModel> {
    boolean existsByTitle(String title);
}