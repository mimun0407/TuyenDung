package com.example.tuyendung1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustryDto {
    Long id;
    @NotEmpty(message = "name can not be empty")
    String code;
    @NotEmpty(message = "name can not be empty")
    String name;
    boolean active;
    String description;
    public IndustryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

