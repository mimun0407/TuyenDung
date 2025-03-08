package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo  extends JpaRepository<EntityLevel,Long>, JpaSpecificationExecutor<EntityLevel> {
}
