package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.LevelDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceILevel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/level")
public class LevelController {
    private final ServiceILevel serviceILevel;
    @PostMapping()
    public ApiResponse<ResponseId> industryPost(@RequestBody @Valid LevelDto levelDto) {
        return ApiResponse
                .<ResponseId>builder()
                .data(serviceILevel.insert(levelDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<LevelDto>> industryList
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name,
             @RequestParam (value = "code",required = false)String code){
        LevelDto levelDto = new LevelDto();
        levelDto.setName(name);
        levelDto.setCode(code);
        return ApiResponse.<PageResponse<LevelDto>>builder()
                .data(serviceILevel.findAll(page, size,levelDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceILevel.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> industryUpdate(@RequestBody @Valid LevelDto levelDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceILevel.Update(levelDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<LevelDto> industryGet(@RequestParam long id) {
        return ApiResponse.<LevelDto>builder()
                .data(serviceILevel.findById(id))
                .build();
    }
}
