package com.example.tuyendung1.controller;
import com.example.tuyendung1.dto.HiringTypeDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIHiringType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hiringType")
public class HiringTypeController {
    private final ServiceIHiringType serviceIHiringType;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid HiringTypeDto hiringTypeDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIHiringType.insert(hiringTypeDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<HiringTypeDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name,
             @RequestParam(value = "code",required = false)String code)
    {
        HiringTypeDto hiringTypeDto = new HiringTypeDto();
        hiringTypeDto.setCode(code);
        hiringTypeDto.setName(name);
        return ApiResponse.<PageResponse<HiringTypeDto>>builder()
                .data(serviceIHiringType.findAll(page, size,hiringTypeDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        serviceIHiringType.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid HiringTypeDto hiringTypeDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIHiringType.Update(hiringTypeDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<HiringTypeDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<HiringTypeDto>builder()
                .data(serviceIHiringType.findById(id))
                .build();
    }
}
