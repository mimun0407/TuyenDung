package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.CandidateDto;
import com.example.tuyendung1.entity.EntityCandidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    EntityCandidate toEntity(CandidateDto candidateRepo);
    CandidateDto toDto(EntityCandidate candidateEntity);

}
