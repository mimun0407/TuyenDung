package com.example.tuyendung1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name ="round")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityRound extends EntityFather{
    String code;
    String description;
    @ManyToOne
    EntityHiringType hiringType;
}
