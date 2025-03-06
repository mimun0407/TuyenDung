package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.CandidateDto;
import com.example.tuyendung1.entity.EntityCandidate;
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
public class SpecCandidate implements Specification<EntityCandidate> {
    CandidateDto candidateDto;
    @Override
    public Predicate toPredicate(Root<EntityCandidate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (candidateDto.getName()!=null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+candidateDto.getName()+"%"));
        }
        if (candidateDto.getCode()!=null){
            predicates.add(criteriaBuilder.equal(root.get("code"), candidateDto.getCode()));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
