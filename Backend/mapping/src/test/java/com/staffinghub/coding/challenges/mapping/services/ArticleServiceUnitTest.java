package com.staffinghub.coding.challenges.mapping.services;

import com.staffinghub.coding.challenges.mapping.mappers.IArticleMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Article;
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto;
import com.staffinghub.coding.challenges.mapping.repositories.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.testng.Assert.assertThrows;

@RunWith(SpringRunner.class)
public class ArticleServiceUnitTest {

    @Mock
    private ArticleRepository repository;

    @Mock
    private IArticleMapper mapper;

    @InjectMocks
    private ArticleService articleService;

    @Test
    public void getList() {
        doReturn(Arrays.asList(createDummyArticle(999L))).when(repository).all();
        doReturn(new ArticleDto()).when(mapper).toArticleDto(any());

        List<ArticleDto> items = articleService.list();
        assertFalse(items.isEmpty());
    }

    @Test
    public void getArticleForIdSuccessfully() {
        long articleId = 999L;

        doReturn(Arrays.asList(createDummyArticle(articleId))).when(repository).all();
        doReturn(new ArticleDto()).when(mapper).toArticleDto(any());

        ArticleDto unMapped = articleService.articleForId(articleId);
        assertNotNull(unMapped);
    }

    @Test
    public void getArticleForIdUnsuccessfully() {
        doReturn(Arrays.asList(createDummyArticle(999L))).when(repository).all();
        doReturn(new ArticleDto()).when(mapper).toArticleDto(any());

        assertThrows(ResponseStatusException.class, () -> articleService.articleForId(-1l));
    }

    @Test
    public void createArticleForIdSuccessfully() {
        doReturn(new ArticleDto()).when(mapper).toArticleDto(any());

        ArticleDto articleDto = new ArticleDto();
        ArticleDto savedDto = articleService.create(articleDto);
        assertNotNull(savedDto);
        assertThat(savedDto).usingRecursiveComparison().isEqualTo(articleDto);
    }

    /**
     * Creates a dummy article object to be used for testing.
     *
     * @param id {@link Long}
     * @return {@link Article}
     */
    private Article createDummyArticle(Long id) {
        final Article result = new Article();
        result.setId(id);
        result.setAuthor("Max Mustermann");
        result.setDescription("Article Description " + id);
        result.setTitle("Article Nr.: " + id);
        result.setLastModifiedBy("Hans Müller");
        result.setLastModified(new Date());
        return result;
    }
}
