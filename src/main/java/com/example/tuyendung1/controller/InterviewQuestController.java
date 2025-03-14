package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.InterviewQuestDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceInterviewQuest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/interviewQuest")
public class InterviewQuestController {

    private final ServiceInterviewQuest serviceInterviewQuest;

    @PostMapping()
    public ApiResponse<ResponseId> addInterviewQuest(@RequestBody @Valid InterviewQuestDto interviewQuestDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceInterviewQuest.insert(interviewQuestDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<InterviewQuestDto>> getInterviewQuestList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code) {

        InterviewQuestDto interviewQuestDto = new InterviewQuestDto();
        interviewQuestDto.setName(name);
        interviewQuestDto.setCode(code);

        return ApiResponse.<PageResponse<InterviewQuestDto>>builder()
                .data(serviceInterviewQuest.findAll(page, size, interviewQuestDto))
                .build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateInterviewQuest(@RequestBody @Valid InterviewQuestDto interviewQuestDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceInterviewQuest.Update(interviewQuestDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteInterviewQuest(@RequestParam long id) {
        serviceInterviewQuest.delete(id);
        return ApiResponse.<String>builder()
                .data("Deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<InterviewQuestDto> getInterviewQuest(@PathVariable long id) {
        return ApiResponse.<InterviewQuestDto>builder()
                .data(serviceInterviewQuest.findById(id))
                .build();
    }
}
