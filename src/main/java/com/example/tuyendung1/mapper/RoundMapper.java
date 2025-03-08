package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.RoundDto;
import com.example.tuyendung1.entity.EntityHiringType;
import com.example.tuyendung1.entity.EntityRound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GroupReasonMapper.class})
public interface RoundMapper {
    RoundDto roundEntityToDto (EntityRound entityRound);
    EntityRound dtoToEntity (RoundDto roundDto);
}
