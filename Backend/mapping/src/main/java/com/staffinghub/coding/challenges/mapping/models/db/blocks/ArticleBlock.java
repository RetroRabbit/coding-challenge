package com.staffinghub.coding.challenges.mapping.models.db.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockDtoMapper;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMappableBlockDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public abstract class ArticleBlock implements IMappableBlockDto {

    private int sortIndex;

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public ArticleBlockDto map(IMainArticleBlockDtoMapper mapper) {
        return mapper.map(this);
    }
}
