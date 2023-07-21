package com.staffinghub.coding.challenges.mapping.mappers

import com.staffinghub.coding.challenges.mapping.models.db.Article
import com.staffinghub.coding.challenges.mapping.models.db.blocks.*
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ArticleBlockDtoFactory
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto
import org.mapstruct.*
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlock as TextBlockDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlock as VideoBlockDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlock as ImageBlockDto
import org.springframework.stereotype.Component

@Component
@Mapper(uses = [ArticleBlockDtoFactory::class])
interface ArticleMapperMapstruct {

    fun mapToGalleryBlockDto(gallery: GalleryBlock): GalleryBlockDto

    fun mapToTextBlockDto(textBlock: TextBlock): TextBlockDto

    fun mapToImageBlockDto(imageBlock: ImageBlock): ImageBlockDto

    fun mapToVideoBlockDto(videoBlock: VideoBlock): VideoBlockDto

    fun mapToArticleDto(article: Article): ArticleDto {
        return ArticleDto(
            id = article.id,
            title = article.title,
            description = article.description.toString(),
            author = article.author.toString(),
            blocks = mapBlocksToDto(article.blocks)
        )
    }

    @IterableMapping(elementTargetType = ArticleBlockDto::class)
    fun mapBlocksToDto(blocks: Collection<ArticleBlock>): Collection<ArticleBlockDto>
    {return blocks.map { mapToArticleBlockDto(it) }}

    fun mapToArticleBlockDto(articleBlock: ArticleBlock): ArticleBlockDto {
        return when (articleBlock) {
            is TextBlock -> mapToTextBlockDto(articleBlock)
            is ImageBlock -> mapToImageBlockDto(articleBlock)
            is VideoBlock -> mapToVideoBlockDto(articleBlock)
            is GalleryBlock -> mapToGalleryBlockDto(articleBlock)
            else -> throw IllegalArgumentException("Unknown ArticleBlock type")
        }
    }
}