package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.ReasonHiringDto;
import com.example.tuyendung1.entity.EntityReasonHiring;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReasonHiringMap {
    EntityReasonHiring dtoToEntity(ReasonHiringDto dto);
    ReasonHiringDto entityToDto(EntityReasonHiring entity);
}