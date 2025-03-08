package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.ReasonHiringDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceReasonHiring;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reasonHiring")
public class ReasonHiringController {

    private final ServiceReasonHiring serviceReasonHiring;

    @PostMapping()
    public ApiResponse<ResponseId> addReasonHiring(@RequestBody @Valid ReasonHiringDto reasonHiringDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceReasonHiring.insert(reasonHiringDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ReasonHiringDto>> getReasonHiringList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "code", required = false) String code) {

        ReasonHiringDto reasonHiringDto = new ReasonHiringDto();
        reasonHiringDto.setCode(code);

        return ApiResponse.<PageResponse<ReasonHiringDto>>builder()
                .data(serviceReasonHiring.findAll(page, size, reasonHiringDto))
                .build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateReasonHiring(@RequestBody @Valid ReasonHiringDto reasonHiringDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceReasonHiring.Update(reasonHiringDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteReasonHiring(@RequestParam long id) {
        serviceReasonHiring.delete(id);
        return ApiResponse.<String>builder()
                .data("Deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ReasonHiringDto> getReasonHiring(@PathVariable long id) {
        return ApiResponse.<ReasonHiringDto>builder()
                .data(serviceReasonHiring.findById(id))
                .build();
    }
}
