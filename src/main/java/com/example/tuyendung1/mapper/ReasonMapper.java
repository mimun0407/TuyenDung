package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.ReasonDto;
import com.example.tuyendung1.entity.EntityReason;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {GroupReasonMapper.class})
public interface ReasonMapper {
    ReasonDto toReasonDto(EntityReason reason);

    EntityReason toEntityReason(ReasonDto reasonDto);
}