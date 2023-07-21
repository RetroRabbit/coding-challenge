package com.staffinghub.coding.challenges.mapping.models.dto.blocks

import com.staffinghub.coding.challenges.mapping.models.db.Image
import com.staffinghub.coding.challenges.mapping.models.db.ImageSize
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ArticleBlock
import org.mapstruct.ObjectFactory
import org.springframework.stereotype.Component
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlock as TextBlockDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlock as VideoBlockDto
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlock as ImageBlockDto
import com.staffinghub.coding.challenges.mapping.models.db.blocks.*
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto

@Component
class ArticleBlockDtoFactory {
    @ObjectFactory
    fun createArticleBlockDto(articleBlock: ArticleBlock): ArticleBlockDto {
        return when (articleBlock) {
            is TextBlock -> TextBlockDto(
                text = articleBlock.text,
                sortIndex = articleBlock.sortIndex
            )

            is ImageBlock -> {
                ImageBlockDto(
                    image = articleBlock.image?.let { mapToImageDto(it) } ?: ImageDto(-1, "", ImageSize.SMALL),
                    sortIndex = articleBlock.sortIndex
                )
            }

            is VideoBlock -> VideoBlockDto(
                url = articleBlock.url,
                type = articleBlock.type,
                sortIndex = articleBlock.sortIndex
            )

            is GalleryBlock -> GalleryBlockDto(
                images = articleBlock.images.mapNotNull { it?.let { image -> mapToImageDto(image) } },
                sortIndex = articleBlock.sortIndex
            )

            else -> throw IllegalArgumentException("Unknown ArticleBlock type")
        }
    }

    private fun mapToImageDto(image: Image): ImageDto {
        return ImageDto(
            id = image.id,
            url = image.url,
            imageSize = image.imageSize
        )
    }
}
