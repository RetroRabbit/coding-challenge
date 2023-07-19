package com.staffinghub.coding.challenges.mapping.mappers

import com.staffinghub.coding.challenges.mapping.models.db.Article
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import mapper.adaptTo
import org.springframework.stereotype.Component
import java.util.*

@Component
class ArticleMapper {
    fun map(article: Article?): ArticleDto {
        val articleDto = article?.adaptTo(ArticleDto::class)
        return articleDto!!
    }

    // Not part of the challenge / Nicht Teil dieser Challenge.
    fun map(articleDto: ArticleDto?): Article = Article(
        title = "An Article",
        blocks = emptySet(),
        id = 1,
        lastModified = Date()
    )
}
