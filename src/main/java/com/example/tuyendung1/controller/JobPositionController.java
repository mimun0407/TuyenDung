package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIJobPosition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/JobPosition")
public class JobPositionController {

    private final ServiceIJobPosition serviceIJobPosition;
    private final MessageSource messageSource;


    @PostMapping
    public ApiResponse<ResponseId> addJobPosition(@RequestBody @Valid JobPositionDto jobPosition,
                                                  @RequestHeader(name = "Accept-Language", required = false) String acceptLanguage) {
        Locale locale = (acceptLanguage != null) ? Locale.forLanguageTag(acceptLanguage) : Locale.getDefault();
        String message = messageSource.getMessage("job.position.add.success", null, locale);
        return ApiResponse.<ResponseId>builder()
                .data(serviceIJobPosition.insert(jobPosition))
                .message(message)
                .build();
    }
    @GetMapping("/list")
    public ApiResponse<PageResponse<JobPositionDto>> getJobPosition
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name" ,required = false)String name,
             @RequestParam(value = "code",required = false)String code)
    {
        JobPositionDto jobPositionDto = new JobPositionDto(name,code);
        return ApiResponse.<PageResponse<JobPositionDto>>builder()
                .data(serviceIJobPosition.findAll(page,size,jobPositionDto)).build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateJobPosition(@RequestBody  @Valid JobPositionDto jobPosition) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIJobPosition.Update(jobPosition))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceIJobPosition.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @GetMapping("/{id}")
    public ApiResponse<JobPositionDto> getJobPosition(@PathVariable long id) {
        return ApiResponse.<JobPositionDto>builder()
                .data(serviceIJobPosition.findById(id))
                .build();
    }
}
