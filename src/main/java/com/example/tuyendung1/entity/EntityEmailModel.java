package com.example.tuyendung1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name ="email_model")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityEmailModel extends EntityFather{
    String title;
    String content;
}
