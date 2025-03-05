package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.PaginateRequest;
import com.example.tuyendung1.dto.responseApi.ResponseId;


public interface ServiceDao<T> {
    ResponseId insert(T t);
    ResponseId Update(T t);
    void delete(Long id);
    T findById(Long id);
    PageResponse<T> findAll(int page, int size,T t );
}
