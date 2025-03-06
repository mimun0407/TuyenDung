package com.example.tuyendung1.dto.Specification;

import com.example.tuyendung1.dto.ChannelDto;
import com.example.tuyendung1.entity.EntityChannel;
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
public class SpecChannel  implements Specification<EntityChannel> {
    ChannelDto channelDto;
    @Override
    public Predicate toPredicate(Root<EntityChannel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (channelDto.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + channelDto.getName() + "%"));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}
