package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.ReasonHiringDto;
import com.example.tuyendung1.dto.Specification.SpecReasonHiring;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityReasonHiring;
import com.example.tuyendung1.mapper.ReasonHiringMap;
import com.example.tuyendung1.repository.ReasonHiringRepo;
import com.example.tuyendung1.service.interfaceService.ServiceReasonHiring;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReasonHiringImp implements ServiceReasonHiring {

    private final ReasonHiringRepo reasonHiringRepo;
    private final ReasonHiringMap reasonHiringMap;

    @Override
    public ResponseId insert(ReasonHiringDto reasonHiringDto) {
        if (reasonHiringRepo.existsByCode(reasonHiringDto.getCode())) {
            throw new RuntimeException("This code already exists");
        }
        EntityReasonHiring entityReasonHiring = reasonHiringMap.dtoToEntity(reasonHiringDto);
        reasonHiringRepo.save(entityReasonHiring);
        return new ResponseId(entityReasonHiring.getId());
    }

    @Override
    public ResponseId Update(ReasonHiringDto reasonHiringDto) {
        EntityReasonHiring entityReasonHiring = reasonHiringRepo.save(reasonHiringMap.dtoToEntity(reasonHiringDto));
        return new ResponseId(entityReasonHiring.getId());
    }

    @Override
    public void delete(Long id) {
        reasonHiringRepo.deleteById(id);
    }

    @Override
    public ReasonHiringDto findById(Long id) {
        EntityReasonHiring entityReasonHiring = reasonHiringRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return reasonHiringMap.entityToDto(entityReasonHiring);
    }

    @Override
    public PageResponse<ReasonHiringDto> findAll(int page, int size, ReasonHiringDto reasonHiringDto) {
        Specification<EntityReasonHiring> specification = new SpecReasonHiring(reasonHiringDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<EntityReasonHiring> pageData = reasonHiringRepo.findAll(specification, pageable);

        return PageResponse.<ReasonHiringDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(reasonHiringMap::entityToDto).toList())
                .build();
    }
}
