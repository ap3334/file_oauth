package com.example.file.app.article.repository;

import com.example.file.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    List<Article> findAllByOrderByIdDesc();

}
