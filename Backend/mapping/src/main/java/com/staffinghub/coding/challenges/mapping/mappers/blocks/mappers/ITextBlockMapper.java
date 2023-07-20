package com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.TextBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlockDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITextBlockMapper {

    TextBlock toTextBlock(TextBlockDto textBlockDto);

    TextBlockDto toTextBlockDto(TextBlock textBlock);
}
