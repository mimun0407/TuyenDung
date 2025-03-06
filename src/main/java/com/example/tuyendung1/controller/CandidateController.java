package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.CandidateDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceICandidate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/candidate")
public class CandidateController {
    private final ServiceICandidate serviceCandidate;
    @PostMapping()
    public ApiResponse<ResponseId> industryPost(@RequestBody @Valid CandidateDto candidateDto) {
        return ApiResponse
                .<ResponseId>builder()
                .data(serviceCandidate.insert(candidateDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<CandidateDto>> industryList
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name,
             @RequestParam (value = "code",required = false)String code){
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setName(name);
        candidateDto.setCode(code);
        return ApiResponse.<PageResponse<CandidateDto>>builder()
                .data(serviceCandidate.findAll(page, size,candidateDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceCandidate.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> industryUpdate(@RequestBody @Valid CandidateDto candidateDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceCandidate.Update(candidateDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<CandidateDto> industryGet(@RequestParam long id) {
        return ApiResponse.<CandidateDto>builder()
                .data(serviceCandidate.findById(id))
                .build();
    }
}
