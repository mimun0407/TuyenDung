package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobPositionMapRepository extends JpaRepository<JobPositionMap, Long> {
    void deleteByJobPositionId(Long id);

    @Query("SELECT jpm FROM JobPositionMap jpm WHERE jpm.jobPosition.id IN :jobPositionIds")
    List<JobPositionMap> findByJobPositionIds(@Param("jobPositionIds") List<Long> jobPositionIds);

    List<JobPositionMap> findAllByJobPositionId(Long id);
}
