package com.example.tuyendung1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Industry extends EntityFather {

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

}

