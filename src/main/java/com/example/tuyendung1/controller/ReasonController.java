package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.ReasonDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIReason;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reason")
public class ReasonController {
    private final ServiceIReason serviceIReason;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid ReasonDto reasonDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIReason.insert(reasonDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ReasonDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        ReasonDto reasonDto = new ReasonDto();
        reasonDto.setName(name);
        return ApiResponse.<PageResponse<ReasonDto>>builder()
                .data(serviceIReason.findAll(page, size,reasonDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        serviceIReason.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid ReasonDto reasonDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIReason.Update(reasonDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<ReasonDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<ReasonDto>builder()
                .data(serviceIReason.findById(id))
                .build();
    }
}
