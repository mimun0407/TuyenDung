package com.example.tuyendung1.dto.responseApi;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    int page;
    int size;
    long totalElements;
    int totalPages;
    int numberOfElements;
    String sortBy;
    List<T> content= Collections.emptyList();
}