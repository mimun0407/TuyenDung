package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityGroupReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupReasonRepo extends JpaRepository<EntityGroupReason,Long>, JpaSpecificationExecutor<EntityGroupReason> {

}
