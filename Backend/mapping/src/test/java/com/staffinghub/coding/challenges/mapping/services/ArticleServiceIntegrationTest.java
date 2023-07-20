package com.staffinghub.coding.challenges.mapping.services;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlockDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertThrows;

public class ArticleServiceIntegrationTest extends ApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void getListOrdered() {
        List<ArticleDto> items = articleService.list();
        assertFalse(items.isEmpty());

        for (ArticleDto block: items) {
            List<ArticleBlockDto> arr = block.getBlocks().stream().sorted(Comparator.comparingLong(articleBlock -> articleBlock.getSortIndex())).collect(Collectors.toList());

            assertThat(block.getBlocks()).usingRecursiveComparison().isEqualTo(arr);
        }
    }

    @Test
    public void getArticleForIdSuccessfully() {
        ArticleDto unMapped = articleService.articleForId(1001l);
        assertNotNull(unMapped);
    }

    @Test
    public void getArticleForIdUnsuccessfully() {
        assertThrows(ResponseStatusException.class, () -> articleService.articleForId(-1l));
    }

    @Test
    public void createArticleForIdSuccessfully() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("Test title");
        articleDto.setDescription("Test description");
        articleDto.setAuthor("Test author");
        TextBlockDto textBlockDto = new TextBlockDto();
        textBlockDto.setText("Test block");
        articleDto.setBlocks(Arrays.asList(textBlockDto));

        ArticleDto savedDto = articleService.create(articleDto);
        assertNotNull(savedDto);
        assertThat(savedDto).usingRecursiveComparison().isEqualTo(articleDto);
    }
}
