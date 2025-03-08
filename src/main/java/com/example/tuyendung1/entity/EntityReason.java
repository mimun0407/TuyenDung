package com.example.tuyendung1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name ="reason")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class EntityReason extends EntityFather{
    String description;
    @ManyToOne
    EntityGroupReason groupReason;
}
