package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityInterviewQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestRepo extends JpaRepository<EntityInterviewQuest, Long>, JpaSpecificationExecutor<EntityInterviewQuest> {
    boolean existsByCode(String code);
}