package com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.ImageBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlockDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IImageBlockMapper {

    ImageBlock toImageBlock(ImageBlockDto imageBlockDto);

    ImageBlockDto toImageBlockDto(ImageBlock imageBlock);
}
