package com.example.tuyendung1.controller.feign;

import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.dto.model.PositionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "positionClient", url = "https://resources-service.dev.apusplatform.com/api/v1/position")
public interface PositionClient {

    @GetMapping("/list")
    ResponseEntity<PositionResponse> getPositions(
            @RequestHeader("authorization") String authorization,
            @RequestHeader("X-TenantId") Integer tenantId,
            @RequestParam("ids") List<Long> ids,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );
}