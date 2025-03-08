package com.example.tuyendung1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name ="quest")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityQuestion extends EntityFather{
    @ManyToOne
    EntityInterviewQuest question;
}
