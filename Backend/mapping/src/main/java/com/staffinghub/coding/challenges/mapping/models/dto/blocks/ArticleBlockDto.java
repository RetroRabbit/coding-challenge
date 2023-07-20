package com.staffinghub.coding.challenges.mapping.models.dto.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMainArticleBlockMapper;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMappableBlock;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ArticleBlock;

public class ArticleBlockDto implements IMappableBlock {
    private int sortIndex;

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    @Override
    public ArticleBlock map(IMainArticleBlockMapper mapper) {
        return mapper.map(this);
    }
}