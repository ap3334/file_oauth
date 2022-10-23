package com.example.file;

import com.example.file.app.article.entity.Article;
import com.example.file.app.article.service.ArticleService;
import com.example.file.app.hashTag.entity.HashTag;
import com.example.file.app.hashTag.service.HashTagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ArticleTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private HashTagService hashTagService;

    @Test
    @DisplayName("1번 게시물에는 키워드가 2개 존재한다.")
    void t2() {
        Article article = articleService.getArticleById(1L);
        List<HashTag> hashTags = hashTagService.getHashTags(article);

        assertThat(hashTags.size()).isEqualTo(2);
    }
}