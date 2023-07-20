package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers.IVideoBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.Article;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlock;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlockType;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlockDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoBlockMapperUnitTest extends ApplicationTests {

    @Autowired
    private IVideoBlockMapper videoBlockMapper;

    @Test
    public void toVideoBlock() {
        final VideoBlockDto videoBlockDto = getVideoBlockDto();

        VideoBlock videoBlock = videoBlockMapper.toVideoBlock(videoBlockDto);

        assertThat(videoBlock)
                .usingRecursiveComparison()
                .isEqualTo(getVideoBlock());
    }

    @Test
    public void toVideoBlockDto() {
        final VideoBlock videoBlock = getVideoBlock();

        VideoBlockDto videoBlockDto = videoBlockMapper.toVideoBlockDto(videoBlock);

        assertThat(videoBlockDto)
                .usingRecursiveComparison()
                .isEqualTo(getVideoBlockDto());
    }

    /**
     * Creates a dummy video block object to be used for testing.
     *
     * @return {@link VideoBlock}
     */
    private static VideoBlock getVideoBlock() {
        final VideoBlock videoBlock = new VideoBlock();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(2);
        return videoBlock;
    }

    /**
     * Creates a dummy video block dto object to be used for testing.
     *
     * @return {@link VideoBlockDto}
     */
    private static VideoBlockDto getVideoBlockDto() {
        final VideoBlockDto videoBlock = new VideoBlockDto();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(2);
        return videoBlock;
    }
}
