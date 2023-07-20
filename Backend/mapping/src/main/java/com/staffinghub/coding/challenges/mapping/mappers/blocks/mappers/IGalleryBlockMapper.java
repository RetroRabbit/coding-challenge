package com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGalleryBlockMapper {

    GalleryBlock toGalleryBlock(GalleryBlockDto galleryBlockDto);

    GalleryBlockDto toGalleryBlockDto(GalleryBlock galleryBlock);
}
