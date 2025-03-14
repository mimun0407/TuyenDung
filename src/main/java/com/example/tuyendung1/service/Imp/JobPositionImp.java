package com.example.tuyendung1.service.Imp;

import com.example.tuyendung1.dto.IndustryDto;
import com.example.tuyendung1.dto.JobPositionDto;
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
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional
    public ResponseId insert(JobPositionDto dto) {
        JobPosition jobPosition=jobPositionMapper.toEntity(dto);
        Industry industry= industryRepo.findById(dto.getIndustryDto().getId()).orElseThrow(()->new RuntimeException("cannot find industry"));
        jobPosition.setIndustry(industry);
        jobPositionRepository.save(jobPosition);
        List<Line>line= dto.getLine();
        List<Department> departments=resourceService.getDepartment(line.stream().map(line1 -> line1.getDepartment().getId()).toList());
        for (int i=0;i<line.size()-1;i++) {
            for (int j=i+1;j<line.size();j++) {
                if (line.get(i).getDepartment().getId().equals(line.get(j).getDepartment().getId())) {
                    throw new RuntimeException("Can not insert duplicate department");
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
            jobPositionMap.setDepartmentId(jobPositionMap.getDepartmentId());
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
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find job position"));

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


    public PageResponse<JobPositionDto> findAll(int page, int size,JobPositionDto jobPositionDto) {
        return null;
    }

    @Transactional
    public ResponseId Update(JobPositionDto dto) {
        JobPosition jobPosition  =jobPositionRepository.findById(dto.getId())
                .orElseThrow(()->new RuntimeException("Can not find job position"));
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