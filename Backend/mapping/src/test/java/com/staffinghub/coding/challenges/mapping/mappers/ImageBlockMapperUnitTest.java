package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers.IImageBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Image;
import com.staffinghub.coding.challenges.mapping.models.db.ImageSize;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ImageBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlockDto;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlockDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageBlockMapperUnitTest extends ApplicationTests {

    @Autowired
    private IImageBlockMapper imageBlockMapper;

    @Test
    public void toImageBlock() {
        final ImageBlockDto imageBlockDto = getImageBlockDto();

        ImageBlock imageBlock = imageBlockMapper.toImageBlock(imageBlockDto);

        assertThat(imageBlock)
                .usingRecursiveComparison()
                .isEqualTo(getImageBlock());
    }

    @Test
    public void toImageBlockDto() {

        final ImageBlock imageBlock = getImageBlock();

        ImageBlockDto imageBlockdto = imageBlockMapper.toImageBlockDto(imageBlock);

        assertThat(imageBlockdto)
                .usingRecursiveComparison()
                .isEqualTo(getImageBlockDto());
    }

    /**
     * Creates a dummy image block object to be used for testing.
     *
     * @return {@link ImageBlock}
     */
    private static ImageBlock getImageBlock() {
        final Image imageResult = new Image();
        imageResult.setId(1L);
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);


        final ImageBlock imageBlock = new ImageBlock();
        imageBlock.setImage(imageResult);
        return imageBlock;
    }

    /**
     * Creates a dummy image block dto object to be used for testing.
     *
     * @return {@link ImageBlockDto}
     */
    private static ImageBlockDto getImageBlockDto() {
        final ImageDto imageResult = new ImageDto();
        imageResult.setId(1L);
        imageResult.setImageSize(ImageSize.LARGE);
        imageResult.setUrl("https://someurl.com/image/" + 1L);

        final ImageBlockDto imageBlock = new ImageBlockDto();
        imageBlock.setImage(imageResult);

        return imageBlock;
    }
}
