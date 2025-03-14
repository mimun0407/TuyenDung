package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.HiringTypeDto;
import com.example.tuyendung1.dto.RoundDto;
import com.example.tuyendung1.dto.Specification.SpecRound;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityHiringType;
import com.example.tuyendung1.entity.EntityRound;
import com.example.tuyendung1.mapper.HiringTypeMapper;
import com.example.tuyendung1.mapper.RoundMapper;
import com.example.tuyendung1.repository.RoundRepo;
import com.example.tuyendung1.service.interfaceService.ServiceIRound;
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
public class RoundImp implements ServiceIRound {
    RoundRepo roundRepo;
    RoundMapper roundMapper;
    HiringTypeMapper hiringTypeMapper;
    @Override
    public ResponseId insert(RoundDto roundDto) {
        EntityRound round= roundMapper.dtoToEntity(roundDto);
        EntityHiringType entityHiringType= hiringTypeMapper.dtoToEntity(roundDto.getHiringTypeDto());
        round.setHiringType(entityHiringType);
        roundRepo.save(round);
        return new ResponseId(round.getId());
    }

    @Override
    public ResponseId Update(RoundDto roundDto) {
        EntityRound round= roundMapper.dtoToEntity(roundDto);
        EntityHiringType entityHiringType= hiringTypeMapper.dtoToEntity(roundDto.getHiringTypeDto());
        round.setHiringType(entityHiringType);
        roundRepo.save(round);
        return new ResponseId(round.getId());
    }

    @Override
    public void delete(Long id) {
        roundRepo.deleteById(id);
    }

    @Override
    public RoundDto findById(Long id) {
        EntityRound round= roundRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find round"));
        RoundDto roundDto=roundMapper.roundEntityToDto(round);
        HiringTypeDto hiringTypeDto=hiringTypeMapper.entityToDto(round.getHiringType());
        roundDto.setHiringTypeDto(hiringTypeDto);
        return roundDto;
    }

    @Override
    public PageResponse<RoundDto> findAll(int page, int size, RoundDto roundDto) {
        Specification<EntityRound> specification = new SpecRound(roundDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = roundRepo.findAll(specification, pageable);

        return PageResponse.<RoundDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(round -> {
                    RoundDto dto = roundMapper.roundEntityToDto(round);
                    dto.setHiringTypeDto(hiringTypeMapper.entityToDto(round.getHiringType()));
                    return dto;
                }).toList())
                .build();
    }
}

