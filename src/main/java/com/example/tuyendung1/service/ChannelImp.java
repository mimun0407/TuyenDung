package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.CandidateDto;
import com.example.tuyendung1.dto.ChannelDto;
import com.example.tuyendung1.dto.GroupReasonDto;
import com.example.tuyendung1.dto.ReasonDto;
import com.example.tuyendung1.dto.Specification.SpecChannel;
import com.example.tuyendung1.dto.Specification.SpecReason;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.EntityCandidate;
import com.example.tuyendung1.entity.EntityChannel;
import com.example.tuyendung1.entity.EntityReason;
import com.example.tuyendung1.mapper.CandidateMapper;
import com.example.tuyendung1.mapper.ChannelMapper;
import com.example.tuyendung1.repository.ChannelRepo;
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
public class ChannelImp implements ServiceIChannel {
    ChannelRepo channelRepo;
    ChannelMapper channelMapper;
    CandidateMapper candidateMapper;


    @Override
    public ResponseId insert(ChannelDto channelDto) {
        EntityChannel channel= channelMapper.channelDtoToChannel(channelDto);
        EntityCandidate candidate= candidateMapper.toEntity(channelDto.getCandidate());
        channel.setCandidate(candidate);
        channelRepo.save(channel);
        return new ResponseId(candidate.getId());
    }

    @Override
    public ResponseId Update(ChannelDto channelDto) {
        EntityChannel channel= channelMapper.channelDtoToChannel(channelDto);
        EntityCandidate candidate= candidateMapper.toEntity(channelDto.getCandidate());
        channel.setCandidate(candidate);
        channelRepo.save(channel);
        return new ResponseId(candidate.getId());
    }

    @Override
    public void delete(Long id) {
        channelRepo.deleteById(id);
    }

    @Override
    public ChannelDto findById(Long id) {
        EntityChannel channel= channelRepo.findById(id).orElseThrow(()->new RuntimeException("Can't find reason"));
        ChannelDto channelDto=channelMapper.channelToChannelDto(channel);
        CandidateDto candidateDto=candidateMapper.toDto(channel.getCandidate());
        channelDto.setCandidate(candidateDto);
        return channelDto;
    }

    @Override
    public PageResponse<ChannelDto> findAll(int page, int size, ChannelDto channelDto) {
        Specification<EntityChannel> specification = new SpecChannel(channelDto);
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = channelRepo.findAll(specification, pageable);

        return PageResponse.<ChannelDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(pageData.getContent().stream().map(channel -> {
                    ChannelDto dto = channelMapper.channelToChannelDto(channel);
                    dto.setCandidate(candidateMapper.toDto(channel.getCandidate()));
                    return dto;
                }).toList())
                .build();
    }
}
