package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.QuestionDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceQuest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/quest")
public class QuestController {
    private final ServiceQuest serviceQuest;
    @PostMapping()
    public ApiResponse<ResponseId> industryPost(@RequestBody @Valid QuestionDto questionDto) {
        return ApiResponse
                .<ResponseId>builder()
                .data(serviceQuest.insert(questionDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<QuestionDto>> industryList
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setName(name);
        return ApiResponse.<PageResponse<QuestionDto>>builder()
                .data(serviceQuest.findAll(page, size,questionDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> industryDelete(@RequestParam long id) {
        serviceQuest.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> industryUpdate(@RequestBody @Valid QuestionDto questionDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceQuest.Update(questionDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<QuestionDto> industryGet(@RequestParam long id) {
        return ApiResponse.<QuestionDto>builder()
                .data(serviceQuest.findById(id))
                .build();
    }
}
