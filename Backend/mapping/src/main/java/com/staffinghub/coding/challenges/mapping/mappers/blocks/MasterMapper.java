package com.staffinghub.coding.challenges.mapping.mappers.blocks;

import com.staffinghub.coding.challenges.mapping.mappers.IArticleMapper;
import com.staffinghub.coding.challenges.mapping.mappers.blocks.mappers.*;
import com.staffinghub.coding.challenges.mapping.models.db.blocks.*;
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterMapper implements IMainArticleBlockDtoMapper, IMainArticleBlockMapper {

    @Autowired
    private IGalleryBlockMapper galleryBlockMapper;

    @Autowired
    private IImageBlockMapper imageBlockMapper;

    @Autowired
    private ITextBlockMapper textBlockMapper;

    @Autowired
    private IVideoBlockMapper videoBlockMapper;

    //<editor-fold desc="To Dto">

    @Override
    public ArticleBlockDto map(ArticleBlock entity) {
        return null;
    }

    @Override
    public GalleryBlockDto map(GalleryBlock entity) {
        return galleryBlockMapper.toGalleryBlockDto(entity);
    }

    @Override
    public ImageBlockDto map(ImageBlock entity) {
        return imageBlockMapper.toImageBlockDto(entity);
    }

    @Override
    public TextBlockDto map(TextBlock entity) {
        return textBlockMapper.toTextBlockDto(entity);
    }

    @Override
    public VideoBlockDto map(VideoBlock entity) {
        return videoBlockMapper.toVideoBlockDto(entity);
    }
    //</editor-fold>

    //<editor-fold desc="From Dto">

    @Override
    public ArticleBlock map(ArticleBlockDto entity) {
        return null;
    }

    @Override
    public GalleryBlock map(GalleryBlockDto entity) {
        return galleryBlockMapper.toGalleryBlock(entity);
    }

    @Override
    public ImageBlock map(ImageBlockDto entity) {
        return imageBlockMapper.toImageBlock(entity);
    }

    @Override
    public TextBlock map(TextBlockDto entity) {
        return textBlockMapper.toTextBlock(entity);
    }

    @Override
    public VideoBlock map(VideoBlockDto entity) {
        return videoBlockMapper.toVideoBlock(entity);
    }
    //</editor-fold>
}
