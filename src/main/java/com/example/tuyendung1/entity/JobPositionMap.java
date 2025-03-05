package com.example.tuyendung1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_position_map")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobPositionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_position_id", nullable = false)
    JobPosition jobPosition;

    @Column(name = "department_id")
    Long departmentId;

    @Column(name = "position_ids", columnDefinition = "bigint[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    List<Long> positionIds;

}

