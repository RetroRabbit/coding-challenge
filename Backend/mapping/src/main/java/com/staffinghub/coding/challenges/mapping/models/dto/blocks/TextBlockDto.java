package com.staffinghub.coding.challenges.mapping.models.dto.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.TextBlock;

public class TextBlockDto extends ArticleBlockDto {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public TextBlock map(IMainArticleBlockMapper mapper) {
        return mapper.map(this);
    }
}
