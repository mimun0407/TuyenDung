package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.GroupReasonDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceIGroupReason;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group_reason")
public class GroupReasonController {
    private final ServiceIGroupReason groupReasonService;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid GroupReasonDto groupDto) {
        return ApiResponse.<ResponseId>builder()
                .data(groupReasonService.insert(groupDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<GroupReasonDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name,
             @RequestParam(value = "code",required = false)String code)
    {
        GroupReasonDto groupReasonDto = new GroupReasonDto();
        groupReasonDto.setCode(code);
        groupReasonDto.setName(name);
        return ApiResponse.<PageResponse<GroupReasonDto>>builder()
                .data(groupReasonService.findAll(page, size,groupReasonDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        groupReasonService.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid GroupReasonDto groupReasonDto) {
        return ApiResponse.<ResponseId>builder()
                .data(groupReasonService.Update(groupReasonDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<GroupReasonDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<GroupReasonDto>builder()
                .data(groupReasonService.findById(id))
                .build();
    }
}
