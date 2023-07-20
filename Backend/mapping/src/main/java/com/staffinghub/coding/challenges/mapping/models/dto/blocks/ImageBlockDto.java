package com.staffinghub.coding.challenges.mapping.models.dto.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ImageBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto;

public class ImageBlockDto extends ArticleBlockDto {

    private ImageDto image;

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    @Override
    public ImageBlock map(IMainArticleBlockMapper mapper) {
        return mapper.map(this);
    }
}
