package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.JobPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long>, JpaSpecificationExecutor<JobPosition> {
    boolean existsJobPositionByCode(String code);
}

