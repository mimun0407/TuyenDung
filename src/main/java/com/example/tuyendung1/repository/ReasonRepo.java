package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityReason;
import com.example.tuyendung1.entity.EntityTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonRepo extends JpaRepository<EntityReason,Long>, JpaSpecificationExecutor<EntityReason> {
}
