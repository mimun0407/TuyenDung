package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.FormOfWorkDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceFormOfWork;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/FormOfWork")
public class FormOfWorkController {

    private final ServiceFormOfWork serviceFormOfWork;

    @PostMapping()
    public ApiResponse<ResponseId> addFormOfWork(@RequestBody @Valid FormOfWorkDto formOfWorkDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceFormOfWork.insert(formOfWorkDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<FormOfWorkDto>> getFormOfWorkList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "description", required = false) String description) {

        FormOfWorkDto formOfWorkDto = new FormOfWorkDto();
        formOfWorkDto.setCode(code);
        formOfWorkDto.setDescription(description);

        return ApiResponse.<PageResponse<FormOfWorkDto>>builder()
                .data(serviceFormOfWork.findAll(page, size, formOfWorkDto))
                .build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateFormOfWork(@RequestBody @Valid FormOfWorkDto formOfWorkDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceFormOfWork.Update(formOfWorkDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteFormOfWork(@RequestParam long id) {
        serviceFormOfWork.delete(id);
        return ApiResponse.<String>builder()
                .data("Deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<FormOfWorkDto> getFormOfWork(@PathVariable long id) {
        return ApiResponse.<FormOfWorkDto>builder()
                .data(serviceFormOfWork.findById(id))
                .build();
    }
}