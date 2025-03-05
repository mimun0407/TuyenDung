package com.example.tuyendung1.dto.responseApi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginateRequest {
    private int page;
    private int size;

    public PaginateRequest() {
        this.page = 0;
        this.size = 5;
    }

    public PaginateRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
