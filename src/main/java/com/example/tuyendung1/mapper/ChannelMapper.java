package com.example.tuyendung1.mapper;

import com.example.tuyendung1.dto.ChannelDto;
import com.example.tuyendung1.entity.EntityChannel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelDto channelToChannelDto(EntityChannel channel);
    EntityChannel channelDtoToChannel(ChannelDto channelDto);
}
