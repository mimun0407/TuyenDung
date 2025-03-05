package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobPositionMapRepository extends JpaRepository<JobPositionMap, Long> {
    void deleteByJobPositionId(Long id);

    List<JobPositionMap> findByJobPosition(JobPosition jobPosition);

    void deleteByJobPosition(JobPosition jobPosition);

    List<JobPositionMap> findAllByJobPositionId(Long id);
}
