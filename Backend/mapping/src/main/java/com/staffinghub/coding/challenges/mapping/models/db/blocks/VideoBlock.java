package com.staffinghub.coding.challenges.mapping.models.db.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockDtoMapper;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlockDto;

public class VideoBlock extends ArticleBlock {

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

    public VideoBlockDto map(IMainArticleBlockDtoMapper mapper) {
        return mapper.map(this);
    }
}
