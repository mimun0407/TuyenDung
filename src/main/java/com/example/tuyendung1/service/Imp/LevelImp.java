package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.LevelDto;
import com.example.tuyendung1.dto.Specification.SpecLevel;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityLevel;
import com.example.tuyendung1.mapper.LevelMapper;
import com.example.tuyendung1.repository.LevelRepo;
import com.example.tuyendung1.service.interfaceService.ServiceILevel;
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
public class LevelImp implements ServiceILevel {
    LevelRepo levelRepo;
    LevelMapper levelMapper;
    @Override
    public ResponseId insert(LevelDto levelDto) {
        EntityLevel level=levelMapper.dtoToEntity(levelDto);
        levelRepo.save(level);
        return new ResponseId(level.getId());
    }

    @Override
    public ResponseId Update(LevelDto levelDto) {
        EntityLevel level=levelMapper.dtoToEntity(levelDto);
        levelRepo.save(level);
        return new ResponseId(level.getId());
    }

    @Override
    public void delete(Long id) {
        levelRepo.deleteById(id);
    }

    @Override
    public LevelDto findById(Long id) {
        EntityLevel level=levelRepo.findById(id).orElseThrow(()->new RuntimeException("cannot find level"));
        return levelMapper.entityToDto(level);
    }

    @Override
    public PageResponse<LevelDto> findAll(int page, int size, LevelDto levelDto) {
        Sort sort=Sort.by("createdAt").descending();
        Pageable pageable= PageRequest.of(page-1,size,sort);
        Specification<EntityLevel> specification=new SpecLevel(levelDto);
        var pageData=levelRepo.findAll(specification,pageable);
        return PageResponse.<LevelDto>builder()
                .size(size)
                .page(page)
                .sortBy(sort.toString())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .content(pageData.getContent().stream().map(levelMapper::entityToDto).collect(Collectors.toList()))
                .build();
    }
}
