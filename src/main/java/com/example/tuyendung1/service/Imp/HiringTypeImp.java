package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.HiringTypeDto;
import com.example.tuyendung1.dto.Specification.SpecHiringType;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityHiringType;
import com.example.tuyendung1.mapper.HiringTypeMapper;
import com.example.tuyendung1.repository.HiringTypeRepo;
import com.example.tuyendung1.service.interfaceService.ServiceIHiringType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HiringTypeImp implements ServiceIHiringType {
    HiringTypeRepo hiringTypeRepo;
    HiringTypeMapper hiringTypeMapper;

    @Override
    public ResponseId insert(HiringTypeDto hiringTypeDto) {
        EntityHiringType entityHiringType = hiringTypeMapper.dtoToEntity(hiringTypeDto);
        entityHiringType = hiringTypeRepo.save(entityHiringType);
        return new ResponseId(entityHiringType.getId());
    }

    @Override
    public ResponseId Update(HiringTypeDto hiringTypeDto) {
        EntityHiringType entityHiringType = hiringTypeMapper.dtoToEntity(hiringTypeDto);
        entityHiringType = hiringTypeRepo.save(entityHiringType);
        return new ResponseId(entityHiringType.getId());
    }

    @Override
    public void delete(Long id) {
        hiringTypeRepo.deleteById(id);
    }

    @Override
    public HiringTypeDto findById(Long id) {
       EntityHiringType entityHiringType= hiringTypeRepo.findById(id).orElseThrow(() -> new RuntimeException("HiringType not found"));
        return hiringTypeMapper.entityToDto(entityHiringType);
    }

    @Override
    public PageResponse<HiringTypeDto> findAll(int page, int size, HiringTypeDto hiringTypeDto) {
        Specification<EntityHiringType> specification=new SpecHiringType(hiringTypeDto);
        Sort sort=Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page-1, size, sort);
        var pageData=hiringTypeRepo.findAll(specification, pageable);
        return PageResponse.<HiringTypeDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(hiringTypeMapper::entityToDto).toList())
                .build();
    }
}
