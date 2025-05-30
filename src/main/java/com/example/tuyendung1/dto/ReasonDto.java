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
public class ReasonDto {
    Long id;
    @NotEmpty(message = "this can not be empty")
    String name;
    String description;
    Boolean isActive;
    GroupReasonDto groupReason;
}
