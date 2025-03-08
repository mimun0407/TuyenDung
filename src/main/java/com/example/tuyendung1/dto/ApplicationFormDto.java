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
public class ApplicationFormDto {
    Long id;

    @NotEmpty(message = "Code cannot be empty")
    String code;

    @NotEmpty(message = "Name cannot be empty")
    String name;
    Boolean active;
    String description;
}
