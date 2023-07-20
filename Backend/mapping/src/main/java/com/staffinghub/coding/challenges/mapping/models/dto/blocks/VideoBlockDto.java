package com.staffinghub.coding.challenges.mapping.models.dto.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlock;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlockType;

public class VideoBlockDto extends ArticleBlockDto {

    private String url;

    private VideoBlockType type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VideoBlockType getType() {
        return type;
    }

    public void setType(VideoBlockType type) {
        this.type = type;
    }

    @Override
    public VideoBlock map(IMainArticleBlockMapper mapper) {
        return mapper.map(this);
    }
}
