package com.staffinghub.coding.challenges.mapping.mappers

import com.staffinghub.coding.challenges.mapping.models.db.Article
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import mapper.adaptTo
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Component
import java.util.*

@Component
class ArticleMapper {
    var mapperStruct:ArticleMapperMapstruct = Mappers.getMapper(ArticleMapperMapstruct::class.java)
    fun map(article: Article): ArticleDto {
        return article.adaptTo(ArticleDto::class)
    }

    /**
     * Object mapper using Mapstruct as alternative to Mapster Dependency
     */
    fun mapStructMap(article: Article): ArticleDto {
        return mapperStruct.mapToArticleDto(article)
    }

    // Not part of the challenge / Nicht Teil dieser Challenge.
    fun map(articleDto: ArticleDto?): Article = Article(
        title = "An Article",
        blocks = emptySet(),
        id = 1,
        lastModified = Date()
    )
}
