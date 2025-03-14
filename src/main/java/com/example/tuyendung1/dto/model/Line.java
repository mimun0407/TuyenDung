package com.example.tuyendung1.dto.model;

import lombok.*;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    Long id;
    Department department;
    Set<Position> positionSet;
}
