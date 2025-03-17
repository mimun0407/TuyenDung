package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.entity.JobPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = Position.class)
public interface JobPositionMapper {
    JobPosition toEntity(JobPositionDto dto);
    JobPositionDto toDto(JobPosition entity);
}