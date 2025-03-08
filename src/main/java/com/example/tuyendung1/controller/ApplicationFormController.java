package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.ApplicationFormDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceApplicationForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/applicationForm")
public class ApplicationFormController {

    private final ServiceApplicationForm serviceApplicationForm;

    @PostMapping()
    public ApiResponse<ResponseId> addApplicationForm(@RequestBody @Valid ApplicationFormDto applicationFormDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceApplicationForm.insert(applicationFormDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ApplicationFormDto>> getApplicationFormList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code) {

        ApplicationFormDto applicationFormDto = new ApplicationFormDto();
        applicationFormDto.setName(name);
        applicationFormDto.setCode(code);

        return ApiResponse.<PageResponse<ApplicationFormDto>>builder()
                .data(serviceApplicationForm.findAll(page, size, applicationFormDto))
                .build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateApplicationForm(@RequestBody @Valid ApplicationFormDto applicationFormDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceApplicationForm.Update(applicationFormDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteApplicationForm(@RequestParam long id) {
        serviceApplicationForm.delete(id);
        return ApiResponse.<String>builder()
                .data("Deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ApplicationFormDto> getApplicationForm(@PathVariable long id) {
        return ApiResponse.<ApplicationFormDto>builder()
                .data(serviceApplicationForm.findById(id))
                .build();
    }
}