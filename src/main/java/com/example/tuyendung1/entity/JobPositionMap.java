package com.example.tuyendung1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

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
    private Long id;

    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne
    private JobPosition jobPosition;

    @ElementCollection
    @Column(name = "position_id")
    private Set<Long> positionId;
}
