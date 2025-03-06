package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<EntityTag,Long>, JpaSpecificationExecutor<EntityTag> {
    Page<EntityTag> findAll(Specification<EntityTag> spec, Pageable pageable);
}
