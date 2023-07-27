package com.staffinghub.coding.challenges.mapping


import com.staffinghub.coding.challenges.mapping.controllers.ArticleController
import com.staffinghub.coding.challenges.mapping.mappers.ArticleMapper
import com.staffinghub.coding.challenges.mapping.models.db.Article
import com.staffinghub.coding.challenges.mapping.models.db.Image
import com.staffinghub.coding.challenges.mapping.models.db.ImageSize
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlockType
import com.staffinghub.coding.challenges.mapping.models.dto.ArticleDto
import com.staffinghub.coding.challenges.mapping.models.dto.ImageDto
import com.staffinghub.coding.challenges.mapping.repositories.ArticleRepository
import com.staffinghub.coding.challenges.mapping.services.ArticleService
import io.kotest.matchers.shouldBe
import mapper.adaptTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import com.staffinghub.coding.challenges.mapping.models.db.blocks.GalleryBlock as GaleryBlockDb
import com.staffinghub.coding.challenges.mapping.models.db.blocks.ImageBlock as ImageBlockDB
import com.staffinghub.coding.challenges.mapping.models.db.blocks.TextBlock as TextBlockDB
import com.staffinghub.coding.challenges.mapping.models.db.blocks.VideoBlock as VideoBlockDB
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto as GaleryBlockDTO
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.ImageBlock as ImageBlockDTO
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.TextBlock as TextBlockDTO
import com.staffinghub.coding.challenges.mapping.models.dto.blocks.VideoBlock as VideoBlockDTO

@ExtendWith(SpringExtension::class, MockitoExtension::class)
@SpringBootTest
class ApplicationTests {

    private lateinit var articleController: ArticleController

    private lateinit var articleService: ArticleService

    @MockBean
    private lateinit var articleRepository: ArticleRepository

    @Autowired
    private lateinit var mapper: ArticleMapper

    @Test
    fun contextLoads() {

    }

    @Test
    fun ArticleServiceTest() {

        val articleId = 1001L

        val article = Article(
            id = articleId,
            title = "Test Article",
            description = "This is a test article",
            author = "Test Author",
            lastModified = Date(),
            blocks = listOf(
                TextBlockDB("Test text 1", 1),
                CreateImageBlockDB(articleId),
                TextBlockDB("Test text 2", 3)
            ).toSet()
        )

        val articleDto = ArticleDto(
            id = articleId,
            title = "Test Article",
            description = "This is a test article",
            author = "Test Author",
            blocks = listOf(
                TextBlockDTO("Test text 1", 1),
                CreateImageBlockDTO(articleId),
                TextBlockDTO("Test text 2", 3)
            )
        )

        articleService = ArticleService(mapper)
        articleController = ArticleController(articleService)

        `when`(articleRepository.findBy(articleId)).thenReturn(article)

        val result = mapper.mapStructMap(article)

        result shouldBe articleDto

        val testArticleSort = articleRepository.findBy(articleId)

        assert(testArticleSort.blocks.elementAt(0).sortIndex <= testArticleSort.blocks.elementAt(1).sortIndex)
    }

    /**
     * Creates instances of both the TextBlock Database and TextBlockDto Data classes with the same values
     * To acertain that after the Database class has been mapped that the classes are equal
     */
    @Test
    fun CheckTextBlockMap() {
        val textBlockDb = TextBlockDB(
            text = "Some Text for $this",
            sortIndex = 0
        )

        val textBlockDto = TextBlockDTO(
            text = "Some Text for $this",
            sortIndex = 0
        )

        assert(textBlockDto == textBlockDb.adaptTo(TextBlockDTO::class))
    }

    /**
     * Creates instances of both the VideoBlock Database and VideoBlockDto Data classes with the same values
     * To acertain that after the VideoBlock Database class has been mapped that the classes are equal
     */
    @Test
    fun CheckVideoBlockMap() {
        val videoBlockDB = VideoBlockDB(
            type = VideoBlockType.YOUTUBE,
            url = "https://youtu.be/myvideo",
            sortIndex = 4
        )

        val videoBlockDTO = VideoBlockDTO(
            type = VideoBlockType.YOUTUBE,
            url = "https://youtu.be/myvideo",
            sortIndex = 4
        )

        assert(videoBlockDTO == videoBlockDB.adaptTo(VideoBlockDTO::class))
    }

    /**
     * Creates instances of both the ImageBlock Database and ImageBlockDto Data classes with the same values
     * To acertain that after the ImageBlock Database class has been mapped that the classes are equal
     */
    @Test
    fun CheckImageBlockMap() {
        val imageBlockDB = CreateImageBlockDB(1L)

        val imageBlockDTO = CreateImageBlockDTO(1L)

        assert(imageBlockDTO == imageBlockDB.adaptTo(imageBlockDTO::class))
    }

    /**
     * Creates instances of both the GalleryBlock Database and GalleryBlockDto Data classes with the same values
     * To acertain that after the GalleryBlock Database class has been mapped that the classes are equal
     */
    @Test
    fun CheckGalleryBlockMap() {
        val galleryBlockDB = GaleryBlockDb(
            sortIndex = 3,
            images = listOf(
                CreateImageBlockDB(1L).image,
                CreateImageBlockDB(2L).image
            )
        )

        val galleryBlockDTO = GaleryBlockDTO(
            sortIndex = 3,
            images = listOf(
                CreateImageBlockDTO(1L).image,
                CreateImageBlockDTO(2L).image
            )
        )

        assert(galleryBlockDTO == galleryBlockDB.adaptTo(GaleryBlockDTO::class))
    }

    /**
     * Creates an instance of the ImageBlock Database object
     *
     * @param imageId the Id of the image which is a long
     *
     * @return ImageBlock database object
     */
    fun CreateImageBlockDB(imageId: Long): ImageBlockDB {
        return ImageBlockDB(
            image = Image(
                url = "https://someurl.com/image/$imageId",
                id = imageId,
                imageSize = ImageSize.LARGE,
                lastModified = Date(),
                lastModifiedBy = "John Doe"
            ),
            sortIndex = 1
        )
    }

    /**
     * Creates an instance of the ImageBlock DTO object
     *
     * @param imageId the Id of the image which is a long
     *
     * @return ImageBlock DTO object
     */
    fun CreateImageBlockDTO(imageId: Long): ImageBlockDTO {
        return ImageBlockDTO(
            image = ImageDto(
                url = "https://someurl.com/image/$imageId",
                id = imageId,
                imageSize = ImageSize.LARGE
            ),
            sortIndex = 1
        )
    }
}