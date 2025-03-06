package com.example.tuyendung1.repository;

import com.example.tuyendung1.entity.EntityChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelRepo extends JpaRepository<EntityChannel,Long>, JpaSpecificationExecutor<EntityChannel> {
}
