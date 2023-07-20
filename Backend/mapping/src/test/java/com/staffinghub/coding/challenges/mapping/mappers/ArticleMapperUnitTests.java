package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.MasterMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Article;
import com.staffinghub.coding.challenges.mapping.models.db.Image;
import com.staffinghub.coding.challenges.mapping.models.db.ImageSize;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.*;
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto;
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleMapperUnitTests extends ApplicationTests {

    @Autowired
    private IArticleMapper articleMapper;

    @Autowired
    private MasterMapper masterMapper;

    @Test
    public void toArticleDto() {
        final Article article = getArticle();

        ArticleDto resultingDto = articleMapper.toArticleDto(article);

        assertThat(resultingDto)
                .usingRecursiveComparison()
                .isEqualTo(getArticleDto());
    }

    @Test
    public void toArticle() {
        final ArticleDto articleDto = getArticleDto();

        Article resulting = articleMapper.toArticle(articleDto);

        Article expectedArticle = getArticle();

        assertThat(resulting)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedBy", "lastModified", "blocks")
                .isEqualTo(expectedArticle);
    }

    /**
     * Creates a dummy article dto object to be used for testing.
     *
     * @return {@link ArticleDto}
     */
    private static ArticleDto getArticleDto() {
        Long id = 1001L;
        final ArticleDto articleDto = new ArticleDto();
        articleDto.setId(id);
        articleDto.setAuthor("Max Mustermann");
        articleDto.setDescription("Article Description " + id);
        articleDto.setTitle("Article Nr.: " + id);

        final Collection<ArticleBlockDto> articleBlockDtoCollection = new HashSet<>();

        final TextBlockDto textBlock = new TextBlockDto();
        textBlock.setText("Some Text for " + id);
        textBlock.setSortIndex(0);
        articleBlockDtoCollection.add(textBlock);

        final ImageDto imageResult = new ImageDto();
        imageResult.setId(1L);
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);

        final ImageBlockDto imageBlock = new ImageBlockDto();
        imageBlock.setImage(imageResult);
        textBlock.setSortIndex(1);
        articleBlockDtoCollection.add(imageBlock);

        final VideoBlockDto videoBlock = new VideoBlockDto();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(2);
        articleBlockDtoCollection.add(videoBlock);

        final ImageDto imageResult2 = new ImageDto();
        imageResult2.setId(101L);
        imageResult2.setImageSize(ImageSize.MEDIUM);
        imageResult2.setUrl("https://someurl.com/image/" + 101L);

        List<ImageDto> images = Arrays.asList(imageResult, imageResult2);

        final GalleryBlockDto galleryBlock = new GalleryBlockDto();
        galleryBlock.setImages(images);
        galleryBlock.setSortIndex(3);
        articleBlockDtoCollection.add(galleryBlock);
        articleDto.setBlocks(articleBlockDtoCollection);
        return articleDto;
    }

    /**
     * Creates a dummy article object to be used for testing.
     *
     * @return {@link Article}
     */
    private static Article getArticle() {
        Long id = 1001L;
        final Article article = new Article();
        article.setId(id);
        article.setAuthor("Max Mustermann");
        article.setDescription("Article Description " + id);
        article.setTitle("Article Nr.: " + id);
        article.setLastModifiedBy("Hans Müller");
        article.setLastModified(new Date());

        final Set<ArticleBlock> articleBlockSet = new HashSet<>();

        final TextBlock textBlock = new TextBlock();
        textBlock.setText("Some Text for " + id);
        textBlock.setSortIndex(0);
        articleBlockSet.add(textBlock);

        final Image imageResult = new Image();
        imageResult.setId(1L);
        imageResult.setLastModified(new Date());
        imageResult.setLastModifiedBy("Max Mustermann");
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);

        final ImageBlock imageBlock = new ImageBlock();
        imageBlock.setImage(imageResult);
        textBlock.setSortIndex(1);
        articleBlockSet.add(imageBlock);

        final VideoBlock videoBlock = new VideoBlock();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(2);
        articleBlockSet.add(videoBlock);

        final Image imageResult2 = new Image();
        imageResult2.setId(101L);
        imageResult2.setLastModified(new Date());
        imageResult2.setLastModifiedBy("Wesley Buck");
        imageResult2.setImageSize(ImageSize.MEDIUM);
        imageResult2.setUrl("https://someurl.com/image/" + 101L);

        List<Image> images = Arrays.asList(imageResult, imageResult2);

        final GalleryBlock galleryBlock = new GalleryBlock();
        galleryBlock.setImages(images);
        galleryBlock.setSortIndex(3);
        articleBlockSet.add(galleryBlock);
        article.setBlocks(articleBlockSet);
        return article;
    }
}