package com.staffinghub.coding.challenges.mapping.models.dto.blocks

import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto

data class ImageBlock(
    var image: ImageDto,
    override val sortIndex: Int,
) : ArticleBlockDto,Comparable<ArticleBlockDto>
{
    override fun compareTo(other: ArticleBlockDto):Int{
        return compareValuesBy(this,other) { it.sortIndex }
    }
}