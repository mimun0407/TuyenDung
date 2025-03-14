package com.example.tuyendung1.dto;

import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.entity.Industry;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.message.Message;

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
    @NotEmpty(message = "name can not be empty")
    String name;
    @NotEmpty(message = "code can not be empty")
    String code;
    String description;
    IndustryDto industryDto;
    List<Line> line=new ArrayList<>();

    public JobPositionDto(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
