package com.staffinghub.coding.challenges.mapping.models.dto.blocks

interface ArticleBlockDto : Comparable<ArticleBlockDto> {
    val sortIndex: Int
    override fun compareTo(other: ArticleBlockDto): Int {
        return compareValuesBy(this, other) { it.sortIndex }
    }
}
