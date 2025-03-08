package com.example.tuyendung1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReasonHiringDto {
    Long id;
    @NotEmpty(message = "Code cannot be empty")
    String code;
    Long DepartmentId;
    String name;
    String description;
}

