package com.staffinghub.coding.challenges.mapping.mappers.blocks;

import com.staffinghub.coding.challenges.mapping.models.db.blocks.*;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.*;

public interface IMainArticleBlockMapper {

    ArticleBlock map(ArticleBlockDto entity);

    GalleryBlock map(GalleryBlockDto entity);

    ImageBlock map(ImageBlockDto entity);

    TextBlock map(TextBlockDto entity);

    VideoBlock map(VideoBlockDto entity);
}
