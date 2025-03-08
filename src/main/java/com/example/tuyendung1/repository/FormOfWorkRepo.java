package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityFormOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormOfWorkRepo extends JpaRepository<EntityFormOfWork, Long>, JpaSpecificationExecutor<EntityFormOfWork> {
    boolean existsByCode(String code);
}