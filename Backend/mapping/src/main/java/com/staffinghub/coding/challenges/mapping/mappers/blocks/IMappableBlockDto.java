package com.staffinghub.coding.challenges.mapping.mappers.blocks;

import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public interface IMappableBlockDto {
    ArticleBlockDto map(IMainArticleBlockDtoMapper mapper);
}
