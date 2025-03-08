package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.EmailModelDto;
import com.example.tuyendung1.entity.EntityEmailModel;
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
public class SpecEntityEmailModel implements Specification<EntityEmailModel> {
    EmailModelDto entityEmailModelDto;

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<EntityEmailModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (entityEmailModelDto.getTitle() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + entityEmailModelDto.getTitle() + "%"));
        }
        if (entityEmailModelDto.getContent() != null) {
            predicates.add(criteriaBuilder.like(root.get("content"), "%" + entityEmailModelDto.getContent() + "%"));
        }
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}