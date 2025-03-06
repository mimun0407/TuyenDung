package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.TagDto;
import com.example.tuyendung1.entity.EntityTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto entityTagToTagDTO(EntityTag tag);
}
