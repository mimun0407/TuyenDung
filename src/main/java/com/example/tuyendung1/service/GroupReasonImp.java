package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.GroupReasonDto;
import com.example.tuyendung1.dto.Specification.SpecGroupReason;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityGroupReason;
import com.example.tuyendung1.mapper.GroupReasonMapper;
import com.example.tuyendung1.repository.GroupReasonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GroupReasonImp implements ServiceIGroupReason{
    final private GroupReasonRepo repo;
    final private GroupReasonMapper groupReasonMapper;
    @Override
    public ResponseId insert(GroupReasonDto groupReasonDto) {
        EntityGroupReason entityGroupReason = groupReasonMapper.dtoToEntityGroupReason(groupReasonDto);
        entityGroupReason = repo.save(entityGroupReason);
        return new ResponseId(entityGroupReason.getId());
    }

    @Override
    public ResponseId Update(GroupReasonDto groupReasonDto) {
        EntityGroupReason entityGroupReason = groupReasonMapper.dtoToEntityGroupReason(groupReasonDto);
        entityGroupReason = repo.save(entityGroupReason);
        return new ResponseId(entityGroupReason.getId());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public GroupReasonDto findById(Long id) {
        return groupReasonMapper.entityGroupReasonToDto(repo.findById(id).get());
    }

    @Override
    public PageResponse<GroupReasonDto> findAll(int page, int size, GroupReasonDto groupReasonDto) {
        Sort sort=Sort.by("createdAt").descending();
        Specification<EntityGroupReason> specification=new SpecGroupReason(groupReasonDto);
        Pageable pageable= PageRequest.of(page-1,size,sort);
        var pageData=repo.findAll(specification, pageable);
        return PageResponse.<GroupReasonDto>builder()
                .size(size)
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .numberOfElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .content(pageData.getContent().stream().map(groupReasonMapper::entityGroupReasonToDto).collect(Collectors.toList()))
                .build();
    }
}
