package com.example.tuyendung1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_position")
public class JobPosition extends EntityFather{
    @JoinColumn(name = "industry_id")
    @ManyToOne
    private Industry industryId;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "code", length = 250)
    private String code;

    @Column(name = "description", length = 250)
    private String description;

}
