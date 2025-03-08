package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.RQCandidateDto;
import com.example.tuyendung1.entity.EntityRQCandidate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecRQCandidate implements Specification<EntityRQCandidate> {
    RQCandidateDto rqCandidateDto;
    @Override
    public Predicate toPredicate(Root<EntityRQCandidate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (rqCandidateDto.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + rqCandidateDto.getName() + "%"));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
