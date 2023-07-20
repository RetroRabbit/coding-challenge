package com.staffinghub.coding.challenges.mapping.mappers.blocks;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.ArticleBlock;

public interface IMappableBlock {
    ArticleBlock map(IMainArticleBlockMapper mapper);
}
