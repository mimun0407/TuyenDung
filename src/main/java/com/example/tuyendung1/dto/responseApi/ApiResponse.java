package com.example.tuyendung1.dto.responseApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ApiResponse<T> {
    @Builder.Default
    int code=200;
    String message;
    T data;
}

