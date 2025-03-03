package com.example.tuyendung1.dto;

import com.example.tuyendung1.dto.model.Line;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobPositionDto {
    String name;
    String code;
    String description;
    List<Line> job_position;

}
