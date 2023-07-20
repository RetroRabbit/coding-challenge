package com.staffinghub.coding.challenges.mapping.models.db.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockDtoMapper;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlockDto;

public class TextBlock extends ArticleBlock {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextBlockDto map(IMainArticleBlockDtoMapper mapper) {
        return mapper.map(this);
    }
}
