package com.example.tuyendung1.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "job_position")
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobPosition extends EntityFather{
    String code;
    String description;
    @ManyToOne
    @JoinColumn(name = "industry_id", referencedColumnName = "id", nullable = false)
    Industry industry;
}
