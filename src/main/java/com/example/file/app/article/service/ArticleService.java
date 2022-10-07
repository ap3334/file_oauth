package com.example.file.app.article.service;

import com.example.file.app.article.entity.Article;
import com.example.file.app.article.repository.ArticleRepository;
import com.example.file.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article write(Long authorId, String subject, String content) {

        Article article = Article
                .builder()
                .author(new Member(authorId))
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }
}

