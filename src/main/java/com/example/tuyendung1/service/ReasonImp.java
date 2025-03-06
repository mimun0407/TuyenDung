package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.GroupReasonDto;
import com.example.tuyendung1.dto.ReasonDto;
import com.example.tuyendung1.dto.Specification.SpecReason;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityGroupReason;
import com.example.tuyendung1.entity.EntityReason;
import com.example.tuyendung1.mapper.GroupReasonMapper;
import com.example.tuyendung1.mapper.ReasonMapper;
import com.example.tuyendung1.repository.ReasonRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReasonImp implements ServiceIReason{
    ReasonRepo reasonRepo;
    ReasonMapper reasonMapper;
    GroupReasonMapper groupReasonMapper;
    @Override
    public ResponseId insert(ReasonDto reasonDto) {
      EntityReason reason= reasonMapper.toEntityReason(reasonDto);
      EntityGroupReason entityGroupReason= groupReasonMapper.dtoToEntityGroupReason(reasonDto.getGroupReason());
      reason.setGroupReason(entityGroupReason);
      reasonRepo.save(reason);
      return new ResponseId(reason.getId());
    }

    @Override
    public ResponseId Update(ReasonDto reasonDto) {
        EntityReason reason= reasonMapper.toEntityReason(reasonDto);
        EntityGroupReason entityGroupReason= groupReasonMapper.dtoToEntityGroupReason(reasonDto.getGroupReason());
        reason.setGroupReason(entityGroupReason);
        reasonRepo.save(reason);
        return new ResponseId(reason.getId());
    }

    @Override
    public void delete(Long id) {
        reasonRepo.deleteById(id);
    }

    @Override
    public ReasonDto findById(Long id) {
        EntityReason reason= reasonRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find reason"));
        ReasonDto reasonDto=reasonMapper.toReasonDto(reason);
        GroupReasonDto groupReasonDto=groupReasonMapper.entityGroupReasonToDto(reason.getGroupReason());
        reasonDto.setGroupReason(groupReasonDto);
        return reasonDto;
    }

    @Override
    public PageResponse<ReasonDto> findAll(int page, int size, ReasonDto reasonDto) {
        Specification<EntityReason> specification = new SpecReason(reasonDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = reasonRepo.findAll(specification, pageable);

        return PageResponse.<ReasonDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(reason -> {
                    ReasonDto dto = reasonMapper.toReasonDto(reason);
                    dto.setGroupReason(groupReasonMapper.entityGroupReasonToDto(reason.getGroupReason()));
                    return dto;
                }).toList())
                .build();
    }
}
