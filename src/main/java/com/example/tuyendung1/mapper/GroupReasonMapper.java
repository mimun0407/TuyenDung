package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.GroupReasonDto;
import com.example.tuyendung1.entity.EntityGroupReason;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupReasonMapper {
    GroupReasonDto entityGroupReasonToDto(EntityGroupReason entityGroupReason);
    EntityGroupReason dtoToEntityGroupReason(GroupReasonDto groupReasonDto);
}
