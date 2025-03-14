package com.example.tuyendung1.controller;

import com.example.tuyendung1.dto.ChannelDto;
import com.example.tuyendung1.dto.responseApi.ApiResponse;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.service.interfaceService.ServiceIChannel;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/channel")
public class ChannelController {

    ServiceIChannel serviceIChannel;

    @PostMapping
    public ApiResponse<ResponseId> create(@RequestBody @Valid ChannelDto channelDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIChannel.insert(channelDto))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<ChannelDto>> ListAll
            (@RequestParam(value = "page", required = false, defaultValue = "1") int page,
             @RequestParam(value = "size", required = false, defaultValue = "20") int size,
             @RequestParam(value = "name",required = false)String name){
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName(name);
        return ApiResponse.<PageResponse<ChannelDto>>builder()
                .data(serviceIChannel.findAll(page, size,channelDto))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> Delete(@RequestParam long id) {
        serviceIChannel.delete(id);
        return ApiResponse.<String>builder()
                .data("deleted").build();
    }

    @PutMapping()
    public ApiResponse<ResponseId> Update(@RequestBody @Valid ChannelDto channelDto) {
        return ApiResponse.<ResponseId>builder()
                .data(serviceIChannel.Update(channelDto))
                .build();
    }

    @GetMapping()
    public ApiResponse<ChannelDto> GetDetail(@RequestParam long id) {
        return ApiResponse.<ChannelDto>builder()
                .data(serviceIChannel.findById(id))
                .build();
    }
}
