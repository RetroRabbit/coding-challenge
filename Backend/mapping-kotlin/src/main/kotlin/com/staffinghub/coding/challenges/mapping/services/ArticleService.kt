package com.staffinghub.coding.challenges.mapping.services

import com.staffinghub.coding.challenges.mapping.mappers.ArticleMapper
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import com.staffinghub.coding.challenges.mapping.repositories.ArticleRepository
import mapper.adaptListTo
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ArticleService(
    private val mapper: ArticleMapper,
) {
    /**
     *@return List of AriticleDto that have been mapped to the data opject with internal Blocks sorted by sortIndex
     */
    fun list(): List<ArticleDto> {
        val articles = ArticleRepository.all()
        return articles.adaptListTo(ArticleDto::class).toList()
    }

    /**
     *@return Single Instance of AriticleDto that have been mapped to the data opject with internal Blocks sorted by sortIndex
     */
    fun articleForId(id: Long): ArticleDto {
        val article = ArticleRepository.findBy(id)

        if (article == null)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Article with suplied ID found")

        return mapper.mapStructMap(article)
    }

    fun create(articleDto: ArticleDto): ArticleDto {
        val article = mapper.map(articleDto)
        ArticleRepository.create(article)
        return mapper.map(article)
    }
}
