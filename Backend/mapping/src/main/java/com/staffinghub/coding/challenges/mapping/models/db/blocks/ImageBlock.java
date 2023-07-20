package com.staffinghub.coding.challenges.mapping.models.db.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockDtoMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Image;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlockDto;

public class ImageBlock extends ArticleBlock {

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageBlockDto map(IMainArticleBlockDtoMapper mapper) {
        return mapper.map(this);
    }
}
