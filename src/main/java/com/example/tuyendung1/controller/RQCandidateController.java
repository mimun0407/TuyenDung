package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.RQCandidateDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIQrCandidate;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rqCandidate")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RQCandidateController {
    ServiceIQrCandidate serviceIQrCandidate;
    @PostMapping()
    public ApiResponse<ResponseId> industryPost(@RequestBody @Valid RQCandidateDto candidateDto) {
        return ApiResponse
                .<ResponseId>builder()
                .data(serviceIQrCandidate.insert(candidateDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<RQCandidateDto>> industryList
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        RQCandidateDto candidateDto = new RQCandidateDto();
        candidateDto.setName(name);
        return ApiResponse.<PageResponse<RQCandidateDto>>builder()
                .data(serviceIQrCandidate.findAll(page, size,candidateDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceIQrCandidate.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> industryUpdate(@RequestBody @Valid RQCandidateDto candidateDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIQrCandidate.Update(candidateDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<RQCandidateDto> industryGet(@RequestParam long id) {
        return ApiResponse.<RQCandidateDto>builder()
                .data(serviceIQrCandidate.findById(id))
                .build();
    }
}
