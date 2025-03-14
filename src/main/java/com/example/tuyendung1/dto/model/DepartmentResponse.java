package com.example.tuyendung1.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentResponse {
    private DepartmentData data;
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DepartmentData {
        private List<Department> content;
    }
}