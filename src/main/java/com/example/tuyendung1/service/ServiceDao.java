package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;

import java.util.List;

public interface ServiceDao<T,F> {
    ResponseId insert(T t);
    ResponseId Update(F f);
    void delete(Long id);
    T findById(Long id);
    PageResponse<T> findAll(int page, int size);
}
