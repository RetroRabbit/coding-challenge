package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMappableBlock;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.IMappableBlockDto;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.MasterMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Article;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class  IArticleMapper {

    @Autowired
    protected MasterMapper masterMapper;

    @Mapping(target="blocks", expression = "java(getArticleBlocks(dto.getBlocks()))")
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    public abstract Article toArticle(ArticleDto dto);

    @InheritInverseConfiguration
    @Mapping(target="blocks", expression = "java(getArticleBlocksDto(article.getBlocks()))")
    public abstract ArticleDto toArticleDto(Article article);

    @Named("getArticleBlocks")
    protected Set<ArticleBlock> getArticleBlocks(Collection<ArticleBlockDto> articleBlockDtos) {
        return articleBlockDtos
                .stream()
                .map(block -> {
                    if (Objects.isNull(block)) {
                        return null;
                    }

                    if (block instanceof IMappableBlock) {
                        return block.map(masterMapper);
                    }

                    // TODO: Add exception handler to mitigate the need to use a try catch.
                    throw new UnsupportedOperationException(String.format("Failed to map %s as it's not implementing Mappable Article Dto Block", block.getClass()));
                })
                .collect(Collectors.toSet());
    }

    @Named("getArticleBlocksDto")
    protected Collection<ArticleBlockDto> getArticleBlocksDto(Set<ArticleBlock> article) {
        return article
                .stream()
                .map(block -> {
                    if (Objects.isNull(block)) {
                        return null;
                    }

                    if (block instanceof IMappableBlockDto) {
                        return block.map(masterMapper);
                    }

                    // TODO: Add exception handler to mitigate the need to use a try catch.
                    throw new UnsupportedOperationException(String.format("Failed to map %s as it's not implementing Mappable Article Block", block.getClass()));
                })
                .sorted(Comparator.comparingLong(articleBlock -> articleBlock.getSortIndex()))
                .collect(Collectors.toList());
    }
}