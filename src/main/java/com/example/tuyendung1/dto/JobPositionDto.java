package com.example.tuyendung1.dto;

import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.entity.Industry;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobPositionDto {
    Long id;
    String name;
    String code;
    String description;
    Industry industry;
    List<Line> line=new ArrayList<>();
}
