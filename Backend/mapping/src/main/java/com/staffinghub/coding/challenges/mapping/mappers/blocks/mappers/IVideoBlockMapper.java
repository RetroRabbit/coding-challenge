package com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlockDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVideoBlockMapper {

    VideoBlock toVideoBlock(VideoBlockDto videoBlockDto);

    VideoBlockDto toVideoBlockDto(VideoBlock videoBlock);
}
