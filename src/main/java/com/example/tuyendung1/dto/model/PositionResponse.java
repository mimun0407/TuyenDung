package com.example.tuyendung1.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionResponse {
    private PositionData data;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class PositionData {
        private List<Position> content;
    }
}