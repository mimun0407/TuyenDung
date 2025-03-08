package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.RQCandidateDto;
import com.example.tuyendung1.entity.EntityRQCandidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GroupReasonMapper.class})
public interface RQCandidateMapper {
    EntityRQCandidate DtoToEntity(RQCandidateDto rqCandidateDto);
    RQCandidateDto EntityToDto(EntityRQCandidate rqCandidate);
}
