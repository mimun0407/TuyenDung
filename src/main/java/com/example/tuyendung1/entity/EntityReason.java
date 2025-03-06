package com.example.tuyendung1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name ="reason")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EntityReason extends EntityFather{
    String description;
    @ManyToOne
    EntityGroupReason groupReason;
}
