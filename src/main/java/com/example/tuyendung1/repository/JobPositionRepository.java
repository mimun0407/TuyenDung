package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.JobPosition;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    @Query(value = """
    SELECT j.id, j.name, j.code, j.description, j.industry_id, 
           m.department_id, m.position_ids 
    FROM job_position j 
    JOIN job_position_map m ON j.id = m.job_position_id
    WHERE (:name IS NULL OR j.name LIKE %:name%)
    ORDER BY j.created_at DESC
    """,
            countQuery = """
    SELECT COUNT(*) 
    FROM job_position j 
    JOIN job_position_map m ON j.id = m.job_position_id
    WHERE (:name IS NULL OR j.name LIKE %:name%)
    """,
            nativeQuery = true)
    Page<Object[]> findAllWithDetails(@Param("name") String name, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = """
    UPDATE job_position_map 
    SET department_id = :departmentId, 
        position_ids = :positionIds
    WHERE job_position_id = :jobPositionId
    """, nativeQuery = true)
    void updateJobPositionMap(@Param("jobPositionId") Long jobPositionId,
                              @Param("departmentId") Long departmentId,
                              @Param("positionIds") Long[] positionIds);

    boolean existsJobPositionByCode(String code);
}
