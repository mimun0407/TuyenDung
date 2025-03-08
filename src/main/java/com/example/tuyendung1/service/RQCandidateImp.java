package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.RQCandidateDto;
import com.example.tuyendung1.dto.Specification.SpecRQCandidate;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityRQCandidate;
import com.example.tuyendung1.mapper.RQCandidateMapper;
import com.example.tuyendung1.repository.CandidateRQRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RQCandidateImp implements ServiceIQrCandidate{
    RQCandidateMapper rqCandidateMapper;
    CandidateRQRepo candidateRQRepo;
    @Override
    public ResponseId insert(RQCandidateDto rqCandidateDto) {
        EntityRQCandidate candidateRQ=rqCandidateMapper.DtoToEntity(rqCandidateDto);
        candidateRQRepo.save(candidateRQ);
        return new ResponseId(candidateRQ.getId());
    }

    @Override
    public ResponseId Update(RQCandidateDto rqCandidateDto) {
        EntityRQCandidate candidateRQ=rqCandidateMapper.DtoToEntity(rqCandidateDto);
        candidateRQRepo.save(candidateRQ);
        return new ResponseId(candidateRQ.getId());
    }

    @Override
    public void delete(Long id) {
        candidateRQRepo.deleteById(id);
    }

    @Override
    public RQCandidateDto findById(Long id) {
        EntityRQCandidate candidateRQ=candidateRQRepo.findById(id).orElseThrow(()->new RuntimeException("RQCandidate not found"));
        return rqCandidateMapper.EntityToDto(candidateRQ);
    }

    @Override
    public PageResponse<RQCandidateDto> findAll(int page, int size, RQCandidateDto rqCandidateDto) {
        Sort sort=Sort.by("createdAt").descending();
        Pageable pageable= PageRequest.of(page-1,size,sort);
        Specification<EntityRQCandidate> specification=new SpecRQCandidate(rqCandidateDto);
        var pageData=candidateRQRepo.findAll(specification,pageable);
        return PageResponse.<RQCandidateDto>builder()
                .size(size)
                .page(page)
                .sortBy(sort.toString())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .content(pageData.getContent().stream().map(rqCandidateMapper::EntityToDto).collect(Collectors.toList()))
                .build();
    }
}
