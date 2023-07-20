package com.staffinghub.coding.challenges.mapping.controllers

import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import com.staffinghub.coding.challenges.mapping.services.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/article")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping
    fun list(): List<ArticleDto> = articleService.list()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    fun details(@PathVariable id: Long): ArticleDto = articleService.articleForId(id)

    @PostMapping
    fun create(@RequestBody articleDto: ArticleDto): ArticleDto = articleService.create(articleDto)
}
