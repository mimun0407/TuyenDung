package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.Industry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepo extends JpaRepository<Industry, Long> {
    boolean existsByCode(String code);
    Page<Industry>findAllByName(String name,Pageable pageable);
    Page<Industry>findAll(Specification<Industry> specification, Pageable pageable);
}