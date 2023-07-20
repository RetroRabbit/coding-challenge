package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers.IGalleryBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Image;
import com.staffinghub.coding.challenges.mapping.models.db.ImageSize;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlockDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GalleryBlockMapperUnitTest extends ApplicationTests {

    @Autowired
    private IGalleryBlockMapper galleryBlockMapper;

    @Test
    public void toGalleryBlockDto() {
        final GalleryBlock galleryBlock = getGalleryBlock();

        GalleryBlockDto galleryBlockDto = galleryBlockMapper.toGalleryBlockDto(galleryBlock);

        assertThat(galleryBlockDto)
                .usingRecursiveComparison()
                .isEqualTo(getGalleryBlockDto());
    }

    @Test
    public void toGalleryBlock() {
        final GalleryBlockDto galleryBlockDto = getGalleryBlockDto();

        GalleryBlock resulting = galleryBlockMapper.toGalleryBlock(galleryBlockDto);

        GalleryBlock expectedGalleryBlock = getGalleryBlock();

        assertThat(resulting)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedBy", "lastModified")
                .isEqualTo(expectedGalleryBlock);
    }

    /**
     * Creates a dummy gallery block object to be used for testing.
     *
     * @return {@link GalleryBlock}
     */
    private static GalleryBlock getGalleryBlock() {
        final Image imageResult = new Image();
        imageResult.setId(1L);
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);

        final Image imageResult2 = new Image();
        imageResult2.setId(101L);
        imageResult2.setImageSize(ImageSize.MEDIUM);
        imageResult2.setUrl("https://someurl.com/image/" + 101L);

        List<Image> images = Arrays.asList(imageResult, imageResult2);

        final GalleryBlock galleryBlock = new GalleryBlock();
        galleryBlock.setImages(images);
        galleryBlock.setSortIndex(3);

        return galleryBlock;
    }

    /**
     * Creates a dummy gallery block dto object to be used for testing.
     *
     * @return {@link GalleryBlockDto}
     */
    private static GalleryBlockDto getGalleryBlockDto() {
        final ImageDto imageResult = new ImageDto();
        imageResult.setId(1L);
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);

        final ImageDto imageResult2 = new ImageDto();
        imageResult2.setId(101L);
        imageResult2.setImageSize(ImageSize.MEDIUM);
        imageResult2.setUrl("https://someurl.com/image/" + 101L);

        List<ImageDto> images = Arrays.asList(imageResult, imageResult2);

        final GalleryBlockDto galleryBlock = new GalleryBlockDto();
        galleryBlock.setImages(images);
        galleryBlock.setSortIndex(3);

        return galleryBlock;
    }
}
