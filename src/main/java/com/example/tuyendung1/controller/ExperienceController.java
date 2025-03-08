package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.ExperienceDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceIExperience;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/experience")
public class ExperienceController {
    ServiceIExperience serviceIExperience;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid ExperienceDto experienceDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIExperience.insert(experienceDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ExperienceDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        ExperienceDto experienceDto = new ExperienceDto();
        experienceDto.setName(name);
        return ApiResponse.<PageResponse<ExperienceDto>>builder()
                .data(serviceIExperience.findAll(page, size,experienceDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        serviceIExperience.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid ExperienceDto experienceDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIExperience.Update(experienceDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<ExperienceDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<ExperienceDto>builder()
                .data(serviceIExperience.findById(id))
                .build();
    }
}
