package com.staffinghub.coding.challenges.mapping.models.db.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockDtoMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Image;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;

import java.util.List;

public class GalleryBlock extends ArticleBlock {

    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public GalleryBlockDto map(IMainArticleBlockDtoMapper mapper) {
        return mapper.map(this);
    }
}
