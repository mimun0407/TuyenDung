package com.example.tuyendung1.service.Imp;
import com.example.tuyendung1.dto.IndustryDto;
import com.example.tuyendung1.dto.JobPositionDto;
import com.example.tuyendung1.dto.Specification.SpecJobPosition;
import com.example.tuyendung1.dto.model.Department;
import com.example.tuyendung1.dto.model.Line;
import com.example.tuyendung1.dto.model.Position;
import com.example.tuyendung1.dto.responseApi.PageResponse;
import com.example.tuyendung1.dto.responseApi.ResponseId;
import com.example.tuyendung1.entity.Industry;
import com.example.tuyendung1.entity.JobPosition;
import com.example.tuyendung1.entity.JobPositionMap;
import com.example.tuyendung1.mapper.IndustryMap;
import com.example.tuyendung1.mapper.JobPositionMapper;
import com.example.tuyendung1.repository.IndustryRepo;
import com.example.tuyendung1.repository.JobPositionMapRepository;
import com.example.tuyendung1.repository.JobPositionRepository;
import com.example.tuyendung1.service.feign.DepartmentService;
import com.example.tuyendung1.service.feign.PositionService;
import com.example.tuyendung1.service.interfaceService.ServiceIJobPosition;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class JobPositionImp implements ServiceIJobPosition {
    private final IndustryRepo industryRepo;
    private final JobPositionRepository jobPositionRepository;
    private final JobPositionMapRepository jobPositionMapRepository;
    private final JobPositionMapper jobPositionMapper;
    private final IndustryMap industryMap;
    private final DepartmentService resourceService;
    private final PositionService positionService;
    private final MessageSource messageSource;
    @Transactional
    public ResponseId insert(JobPositionDto dto) {
        JobPosition jobPosition=jobPositionMapper.toEntity(dto);
        Industry industry= industryRepo.findById(dto.getIndustryDto().getId()).orElseThrow(()->new RuntimeException("cannot find industry"));
        jobPosition.setIndustry(industry);
        jobPositionRepository.save(jobPosition);
        List<Line>line= dto.getLine();
        for (int i=0;i<line.size()-1;i++) {
            for (int j=i+1;j<line.size();j++) {
                if (line.get(i).getDepartment().getId().equals(line.get(j).getDepartment().getId())) {
                    String ms=messageSource.getMessage("line.insert.error",null,Locale.getDefault());
                    throw new RuntimeException(ms);
                }
            }
        }

        JobPositionMap jobPositionMap=new JobPositionMap();
        for (Line line1:line) {
            Set<Position> position=line1.getPositionSet();
            List<Long> positionIDs=new ArrayList<>();
            for (Position position1:position) {
                positionIDs.add(position1.getId());
            }
            jobPositionMap.setPositionIds(positionIDs);
            jobPositionMap.setJobPosition(jobPosition);
            jobPositionMap.setDepartmentId(line1.getDepartment().getId());
            jobPositionMapRepository.save(jobPositionMap);

        }
        return new ResponseId(jobPosition.getId());
    }

    @Transactional
    public void delete(Long id) {
        jobPositionMapRepository.deleteByJobPositionId(id);
        jobPositionRepository.deleteById(id);
    }

    public JobPositionDto findById(Long id) {
        String ms = messageSource.getMessage("id.cannot.found", null, LocaleContextHolder.getLocale());
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ms));

        JobPositionDto jobPositionDto = jobPositionMapper.toDto(jobPosition);
        jobPositionDto.setIndustryDto(new IndustryDto(jobPosition.getIndustry().getId(), jobPosition.getIndustry().getName()));

        List<JobPositionMap> mapList = jobPositionMapRepository.findAllByJobPositionId(id);
        List<Long> departmentIds = mapList.stream().map(JobPositionMap::getDepartmentId).toList();
        List<Department> departments = resourceService.getDepartment(departmentIds);
        Map<Long, Department> departmentMap = departments.stream()
                .collect(Collectors.toMap(Department::getId, Function.identity()));

        // Lấy danh sách tất cả positionId từ JobPositionMap
        List<Long> positionIds = mapList.stream()
                .flatMap(map -> map.getPositionIds().stream())
                .distinct()
                .toList();

        // Gọi PositionService để lấy danh sách Position đầy đủ
        List<Position> positions = positionService.getDepartment(positionIds);

        // Tạo Map để tra cứu nhanh Position theo ID
        Map<Long, String> positionNameMap = positions.stream()
                .collect(Collectors.toMap(Position::getId, Position::getName));

        List<Line> lines = mapList.stream().map(map -> {
            Line line = new Line();
            line.setId(map.getId());
            line.setDepartment(departmentMap.get(map.getDepartmentId()));

            Set<Position> positionSet = map.getPositionIds().stream()
                    .map(positionId -> new Position(positionId, positionNameMap.get(positionId)))
                    .collect(Collectors.toSet());

            line.setPositionSet(positionSet);
            return line;
        }).collect(Collectors.toList());

        jobPositionDto.setLine(lines);
        return jobPositionDto;
    }


    public PageResponse<JobPositionDto> findAll(int page, int size, JobPositionDto jobPositionDto) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Specification<JobPosition> specification = new SpecJobPosition(jobPositionDto);
        Page<JobPosition> jobData = jobPositionRepository.findAll(specification, pageable);
        List<JobPosition> jobDataList = jobData.getContent();
        List<JobPositionDto> jobPositionDtoList = new ArrayList<>();
        for (JobPosition jobPosition:jobDataList) {
            JobPositionDto jobPositionDto1=jobPositionMapper.toDto(jobPosition);
            IndustryDto industryDto=new IndustryDto(jobPosition.getIndustry().getId(), jobPosition.getIndustry().getName());
            jobPositionDto1.setIndustryDto(industryDto);
            jobPositionDtoList.add(jobPositionDto1);
        }
        return PageResponse.<JobPositionDto>builder()
                .totalPages(jobData.getTotalPages())
                .totalElements(jobData.getTotalElements())
                .sortBy(sort.toString())
                .page(page)
                .size(size)
                .numberOfElements(jobData.getNumberOfElements())
                .content(jobPositionDtoList)
                .build();

    }
    @Transactional
    public ResponseId Update(JobPositionDto dto) {
        String ms=messageSource.getMessage("id.cannot.found",null,Locale.getDefault());
        JobPosition jobPosition  =jobPositionRepository.findById(dto.getId())
                .orElseThrow(()->new RuntimeException(ms));
        jobPosition.setId(dto.getId());
        jobPosition.setName(dto.getName());
        jobPosition.setDescription(dto.getDescription());
        jobPosition.setCode(dto.getCode());

        jobPosition.setIndustry(industryMap.industryDtoToIndustry(dto.getIndustryDto()));

        List<JobPositionMap> listJobMap=jobPositionMapRepository.findAllByJobPositionId(dto.getId());
        List<Line> line = dto.getLine();

        for (Line line1 : line) {
            JobPositionMap jobPositionMap;
            if (line1.getId() != null) {
                jobPositionMap = jobPositionMapRepository.findById(line1.getId())
                        .orElseThrow(() -> new RuntimeException("cannot find job position Map"));
                jobPositionMap.setDepartmentId(line1.getDepartment().getId());
                List<Long> positionId = line1.getPositionSet().stream().map(Position::getId).toList();
                jobPositionMap.setPositionIds(positionId);
            } else {
                jobPositionMap = new JobPositionMap();
                jobPositionMap.setDepartmentId(line1.getDepartment().getId());
                List<Long> positionId = line1.getPositionSet().stream().map(Position::getId).toList();
                jobPositionMap.setPositionIds(positionId);
            }
            jobPositionMap.setDepartmentId(line1.getDepartment().getId());
            jobPositionMap.setJobPosition(jobPosition);
            jobPositionMapRepository.save(jobPositionMap);

        }
        listJobMap.removeIf(map -> line.stream().anyMatch(line1 -> map.getId().equals(line1.getId())));
        for (JobPositionMap map : listJobMap) {
            jobPositionMapRepository.deleteById(map.getId());
        }
        return new ResponseId(jobPosition.getId());
    }
}