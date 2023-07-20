package com.staffinghub.coding.challenges.mapping.services;

import com.staffinghub.coding.challenges.mapping.mappers.IArticleMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Article;
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto;
import com.staffinghub.coding.challenges.mapping.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    private final IArticleMapper mapper;

    @Autowired
    public ArticleService(ArticleRepository repository, IArticleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ArticleDto> list() {
        return repository
                .all()
                .stream()
                .map(mapper::toArticleDto)
                .collect(Collectors.toList());
    }

    public ArticleDto articleForId(Long id) {
        // This isn't a good solution, ideally connecting to a database allows us to query for a single entity.
        Optional<Article> article = repository
                .all()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();

        if (!article.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found.");
        }

        return mapper.toArticleDto(article.get());
    }

    public ArticleDto create(ArticleDto articleDto) {
        final Article create = mapper.toArticle(articleDto);
        repository.create(create);
        return mapper.toArticleDto(create);
    }
}
