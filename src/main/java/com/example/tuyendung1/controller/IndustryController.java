package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.IndustryDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceIndustry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/industry")
public class IndustryController {
    private final ServiceIndustry serviceIndustry;

    @PostMapping()
    public ApiResponse<ResponseId> industryPost(@RequestBody IndustryDto industryDto) {
        return ApiResponse
                .<ResponseId>builder()
                .data(serviceIndustry.insert(industryDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<IndustryDto>> industryList
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
    @RequestParam(value = "name",required = false) String name) {
        return ApiResponse.<PageResponse<IndustryDto>>builder()
                .data(serviceIndustry.findAll(page, size,name))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceIndustry.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> industryUpdate(@RequestBody IndustryDto industryDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIndustry.Update(industryDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<IndustryDto> industryGet(@RequestParam long id) {
        return ApiResponse.<IndustryDto>builder()
                .data(serviceIndustry.findById(id))
                .build();
    }
}
