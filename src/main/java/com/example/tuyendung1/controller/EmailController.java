package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.EmailModelDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.ServiceEntityEmailModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/emailModel")
public class EmailController {

    private final ServiceEntityEmailModel serviceEntityEmailModel;

    @PostMapping()
    public ApiResponse<ResponseId> addEntityEmailModel(@RequestBody @Valid EmailModelDto entityEmailModelDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceEntityEmailModel.insert(entityEmailModelDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<EmailModelDto>> getEntityEmailModelList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content) {

        EmailModelDto entityEmailModelDto = new EmailModelDto();
        entityEmailModelDto.setTitle(title);
        entityEmailModelDto.setContent(content);

        return ApiResponse.<PageResponse<EmailModelDto>>builder()
                .data(serviceEntityEmailModel.findAll(page, size, entityEmailModelDto))
                .build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> updateEntityEmailModel(@RequestBody @Valid EmailModelDto entityEmailModelDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceEntityEmailModel.Update(entityEmailModelDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteEntityEmailModel(@RequestParam long id) {
        serviceEntityEmailModel.delete(id);
        return ApiResponse.<String>builder()
                .data("Deleted")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<EmailModelDto> getEntityEmailModel(@PathVariable long id) {
        return ApiResponse.<EmailModelDto>builder()
                .data(serviceEntityEmailModel.findById(id))
                .build();
    }
}
