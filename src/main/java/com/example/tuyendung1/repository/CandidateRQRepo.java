package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityRQCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRQRepo extends JpaRepository<EntityRQCandidate,Long>, JpaSpecificationExecutor<EntityRQCandidate> {
}
