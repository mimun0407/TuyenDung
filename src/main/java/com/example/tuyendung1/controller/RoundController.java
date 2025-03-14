package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.RoundDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIRound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/round")
public class RoundController {

    private final ServiceIRound serviceIRound;

    @PostMapping()
    public ApiResponse<ResponseId> addJobPosition(@RequestBody @Valid RoundDto roundDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIRound.insert(roundDto) )
                .build();
    }
    @GetMapping("/list")
    public ApiResponse<PageResponse<RoundDto>> getJobPosition
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name" ,required = false)String name,
             @RequestParam(value = "code",required = false)String code)
    {
        RoundDto roundDto = new RoundDto();
        roundDto.setName(name);
        roundDto.setCode(code);
        return ApiResponse. <PageResponse<RoundDto>>builder()
                .data(serviceIRound.findAll(page,size,roundDto)).build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateJobPosition(@RequestBody  @Valid RoundDto roundDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIRound.Update(roundDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceIRound.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoundDto> getJobPosition(@PathVariable long id) {
        return ApiResponse.<RoundDto>builder()
                .data(serviceIRound.findById(id))
                .build();
    }
}
