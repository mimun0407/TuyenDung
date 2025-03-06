package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepo extends JpaRepository<EntityTag,Long>, JpaSpecificationExecutor<EntityTag> {
}
