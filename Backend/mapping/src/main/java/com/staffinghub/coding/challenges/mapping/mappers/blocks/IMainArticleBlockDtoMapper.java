package com.staffinghub.coding.challenges.mapping.mappers.blocks;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.*;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.*;

public interface IMainArticleBlockDtoMapper {

    ArticleBlockDto map(ArticleBlock entity);

    GalleryBlockDto map(GalleryBlock entity);

    ImageBlockDto map(ImageBlock entity);

    TextBlockDto map(TextBlock entity);

    VideoBlockDto map(VideoBlock entity);
}
