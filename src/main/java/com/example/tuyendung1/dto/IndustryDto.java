package com.example.tuyendung1.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IndustryDto {
    String code;
    String name;
    boolean active;
    String description;
}

