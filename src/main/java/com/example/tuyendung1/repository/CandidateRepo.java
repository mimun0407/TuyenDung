package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<EntityCandidate,Long>, JpaSpecificationExecutor<EntityCandidate> {
}
