package com.staffinghub.coding.challenges.mapping.mappers;

import com.staffinghub.coding.challenges.mapping.ApplicationTests;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers.ITextBlockMapper;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.TextBlock;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlock;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlockDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TextBlockMapperUnitTest extends ApplicationTests {

    @Autowired
    private ITextBlockMapper textBlockMapper;

    @Test
    public void toTextBlock() {
        final TextBlockDto textBlockDto = getTextBlockDto();

        TextBlock textBlock = textBlockMapper.toTextBlock(textBlockDto);

        assertThat(textBlock)
                .usingRecursiveComparison()
                .isEqualTo(getTextBlock());
    }

    @Test
    public void toTextBlockDto() {
        final TextBlock textBlock = getTextBlock();

        TextBlockDto textBlockDto = textBlockMapper.toTextBlockDto(textBlock);

        assertThat(textBlockDto)
                .usingRecursiveComparison()
                .isEqualTo(getTextBlockDto());
    }

    /**
     * Creates a dummy text block object to be used for testing.
     *
     * @return {@link TextBlock}
     */
    private static TextBlock getTextBlock() {
        final TextBlock textBlock = new TextBlock();
        textBlock.setText("Some Text");
        textBlock.setSortIndex(0);
        return textBlock;
    }

    /**
     * Creates a dummy text block dto object to be used for testing.
     *
     * @return {@link TextBlockDto}
     */
    private static TextBlockDto getTextBlockDto() {
        final TextBlockDto textBlock = new TextBlockDto();
        textBlock.setText("Some Text");
        textBlock.setSortIndex(0);
        return textBlock;
    }
}
