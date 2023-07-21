package com.staffinghub.coding.challenges.mapping.models.dto.blocks

import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlockType

data class VideoBlock(
    var url: String,
    var type: VideoBlockType,
    override val sortIndex: Int,
) : ArticleBlockDto, Comparable<ArticleBlockDto> {
    override fun compareTo(other: ArticleBlockDto): Int {
        return compareValuesBy(this, other) { it.sortIndex }
    }
}