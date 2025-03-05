package com.example.tuyendung1.service;

import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.model.Department;
import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.Industry;
import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import com.example.tuyendung1.mapper.JobPositionMapper;
import com.example.tuyendung1.repository.IndustryRepo;
import com.example.tuyendung1.repository.JobPositionMapRepository;
import com.example.tuyendung1.repository.JobPositionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class JobPositionImp implements ServiceIJobPosition {
    private final IndustryRepo industryRepo;
    private final JobPositionRepository jobPositionRepository;
    private final JobPositionMapRepository jobPositionMapRepository;
    private final JobPositionMapper jobPositionMapper;


    @Transactional
    public ResponseId insert(JobPositionDto dto) {
        if(jobPositionRepository.existsJobPositionByCode(dto.getCode())) {
            throw new RuntimeException("this code already exists");
        }
        try {
            JobPosition jobPosition = jobPositionMapper.toEntity(dto);
            jobPosition = jobPositionRepository.save(jobPosition);
            jobPositionRepository.flush();

            JobPosition finalJobPosition = jobPosition;
            List<JobPositionMap> jobPositionMaps = dto.getLine().stream()
                    .map(line -> {
                        JobPositionMap jobPositionMap = jobPositionMapper.toJobPositionMap(line, finalJobPosition);
                        jobPositionMap.setId(null);
                        return jobPositionMap;
                    })
                    .collect(Collectors.toList());

            System.out.println("JobPositionMaps to insert: " + jobPositionMaps);

            jobPositionMapRepository.saveAll(jobPositionMaps);
            jobPositionMapRepository.flush();

            System.out.println("Inserted JobPositionMaps Successfully");

            return new ResponseId(jobPosition.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Transactional
    public void delete(Long id) {
        jobPositionMapRepository.deleteByJobPositionId(id);
        jobPositionRepository.deleteById(id);
    }

    public JobPositionDto findById(Long id) {
        JobPosition jobPosition= jobPositionRepository.findById(id).orElseThrow(() -> new RuntimeException("JobPosition not found with id: " + id));
        List<JobPositionMap> jobPositionMap = jobPositionMapRepository.findAllByJobPositionId(id);
        Industry industry= industryRepo.findById(jobPosition.getIndustry().getId()).orElseThrow(() -> new RuntimeException("industry not found with id: " + id));
        JobPositionDto jobPositionDto=new JobPositionDto();

        List<Line> line=new ArrayList<>();
        for (JobPositionMap jobPositionMap1 : jobPositionMap) {
            Department department = new Department(jobPositionMap1.getDepartmentId());
            Set<Long> positionIdsSet = new HashSet<>(jobPositionMap1.getPositionIds());
            Set<Position> positions=new HashSet<>();
            for (Long positionId : positionIdsSet) {
                positions.add(new Position(positionId));
            }
            line.add(new Line(department, positions));
        }
        jobPositionDto.setIndustry(industry);
        jobPositionDto.setLine(line);
        jobPositionDto.setCode(jobPosition.getCode());
        jobPositionDto.setName(jobPosition.getName());
        jobPositionDto.setDescription(jobPosition.getDescription());
        return jobPositionDto;
    }

    public PageResponse<JobPositionDto> findAll(int page, int size,String name) {

        Sort sort = Sort.by("created_at").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Object[]> pageData = jobPositionRepository.findAllWithDetails(name,pageable);

        List<JobPositionDto> jobPositionDtos = pageData.getContent().stream().map(obj -> {
            JobPositionDto dto = new JobPositionDto();
            dto.setName((String) obj[1]);
            dto.setCode((String) obj[2]);
            dto.setDescription((String) obj[3]);

            Industry industry = new Industry();
            industry.setId(((Number) obj[4]).longValue());
            dto.setIndustry(industry);

            Department department = new Department(((Number) obj[5]).longValue());

            List<Long> positionIds = Arrays.asList((Long[]) obj[6]);
            Set<Position> positions = positionIds.stream().map(Position::new).collect(Collectors.toSet());

            dto.setLine(List.of(new Line(department, positions)));

            return dto;
        }).collect(Collectors.toList());

        return PageResponse.<JobPositionDto>builder()
                .page(page)
                .size(size)
                .totalElements(pageData.getTotalElements())
                .sortBy(sort.toString())
                .totalPages(pageData.getTotalPages())
                .numberOfElements(pageData.getNumberOfElements())
                .content(jobPositionDtos)
                .build();
    }
    @Transactional
    public ResponseId Update(JobPositionDto dto) {
        Long id = dto.getId();
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobPosition not found with id: " + id));
        jobPosition.setName(dto.getName());
        jobPosition.setDescription(dto.getDescription());
        jobPosition.setCode(dto.getCode());
        jobPosition.setIndustry(dto.getIndustry());

        jobPositionRepository.save(jobPosition);
        List<Line> lines = dto.getLine();
        List<JobPositionMap> jobPositionMaps = jobPositionMapRepository.findAllByJobPositionId(id);
        for (int i = 0; i < lines.size(); i++) {
            JobPositionMap jobPositionMapP = jobPositionMaps.get(i);
            Line line = lines.get(i);
            Long departmentId = line.getDepartment().getId();
            jobPositionMapP.setDepartmentId(departmentId);
            Set<Position> position= line.getPositionSet();
            List<Position> lists=new ArrayList<>(position);
            List<Long> positionIdsSet = new ArrayList<>();
            for (int j = 0; j < position.size(); j++) {
                Long c=lists.get(j).getId();
                positionIdsSet.add(c);
            }
            jobPositionMapP.setPositionIds(positionIdsSet);
            jobPositionMaps.add(jobPositionMapP);
            }
            jobPositionMapRepository.saveAll(jobPositionMaps);
        return new ResponseId(id);
    }
}