package com.staffinghub.coding.challenges.mapping.services

import com.staffinghub.coding.challenges.mapping.repositories.ArticleRepository
import com.staffinghub.coding.challenges.mapping.mappers.ArticleMapper
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import mapper.adaptListTo
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val mapper: ArticleMapper,
) {
    /**
     *@return List of AriticleDto that have been mapped to the data opject with internal Blocks sorted by sortIndex
     */
    fun list(): List<ArticleDto> {

        val articles = ArticleRepository.all()
        val articleDtoList = articles.adaptListTo(ArticleDto::class)
        return articleDtoList.toList()
    }
    /**
     *@return Single Instance of AriticleDto that have been mapped to the data opject with internal Blocks sorted by sortIndex
     */
    fun articleForId(id: Long): ArticleDto {
        val article = ArticleRepository.findBy(id)
        return mapper.map(article)
    }

    fun create(articleDto: ArticleDto): ArticleDto {
        val article = mapper.map(articleDto)
        ArticleRepository.create(article)
        return mapper.map(article)
    }
}
