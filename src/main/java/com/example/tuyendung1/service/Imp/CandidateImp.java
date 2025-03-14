package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.CandidateDto;
import com.example.tuyendung1.dto.Specification.SpecCandidate;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityCandidate;
import com.example.tuyendung1.mapper.CandidateMapper;
import com.example.tuyendung1.repository.CandidateRepo;
import com.example.tuyendung1.service.interfaceService.ServiceICandidate;
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
public class CandidateImp implements ServiceICandidate {
    CandidateRepo candidateRepo;
    CandidateMapper candidateMapper;
    @Override
    public ResponseId insert(CandidateDto candidateDto) {
        EntityCandidate entityCandidate=candidateMapper.toEntity(candidateDto);
        candidateRepo.save(entityCandidate);
        return new ResponseId(entityCandidate.getId());
    }

    @Override
    public ResponseId Update(CandidateDto candidateDto) {
        EntityCandidate entityCandidate=candidateMapper.toEntity(candidateDto);
        candidateRepo.save(entityCandidate);
        return new ResponseId(entityCandidate.getId());
    }

    @Override
    public void delete(Long id) {
        candidateRepo.deleteById(id);
    }

    @Override
    public CandidateDto findById(Long id) {
        return candidateMapper.toDto(candidateRepo.findById(id).get());
    }

    @Override
    public PageResponse<CandidateDto> findAll(int page, int size, CandidateDto candidateDto) {
        Sort sort=Sort.by("createdAt").descending();
        Pageable pageable= PageRequest.of(page-1,size,sort);
        Specification<EntityCandidate> specification=new SpecCandidate(candidateDto);
        var pageData=candidateRepo.findAll(specification,pageable);
        return PageResponse.<CandidateDto>builder()
                .size(size)
                .page(page)
                .sortBy(sort.toString())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .content(pageData.getContent().stream().map(candidateMapper::toDto).collect(Collectors.toList()))
                .build();
    }
}
