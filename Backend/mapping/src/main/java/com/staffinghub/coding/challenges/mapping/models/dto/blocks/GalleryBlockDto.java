package com.staffinghub.coding.challenges.mapping.models.dto.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto;

import java.util.List;

public class GalleryBlockDto extends ArticleBlockDto {

    private List<ImageDto> images;

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    @Override
    public GalleryBlock map(IMainArticleBlockMapper mapper) {
        return mapper.map(this);
    }
}
