package com.staffinghub.coding.challenges.mapping.models.dto.blocks

data class TextBlock(
    var text: String,
    override val sortIndex: Int,
) : ArticleBlockDto,Comparable<ArticleBlockDto>
{
    override fun compareTo(other: ArticleBlockDto):Int{
        return compareValuesBy(this,other) { it.sortIndex }
    }
}