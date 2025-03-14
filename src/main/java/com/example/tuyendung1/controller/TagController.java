package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.TagDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceITag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tag")
public class TagController {

    private final ServiceITag tagService;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid TagDto tagDto) {
        return ApiResponse.<ResponseId>builder()
                .data(tagService.insert(tagDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<TagDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        TagDto tagDto = new TagDto();
        tagDto.setName(name);
        return ApiResponse.<PageResponse<TagDto>>builder()
                .data(tagService.findAll(page, size,tagDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        tagService.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid TagDto tagDto) {
        return ApiResponse.<ResponseId>builder()
                .data(tagService.Update(tagDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<TagDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<TagDto>builder()
                .data(tagService.findById(id))
                .build();
    }
}
