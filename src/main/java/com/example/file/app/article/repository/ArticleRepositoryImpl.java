package com.example.file.app.article.repository;


import com.example.file.app.article.entity.Article;
import com.example.file.util.Util;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.file.app.article.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> getQslArticlesOrderByIdDesc() {
        return jpaQueryFactory
                .select(article)
                .from(article)
                .orderBy(article.id.desc())
                .fetch();
    }

    @Override
    public List<Article> searchQsl(String kwType, String kw) {
        if (Util.str.eq(kwType, "hashTag") && Util.str.empty(kw)) {
            // 키워드를 기반으로 한 검색
            return jpaQueryFactory
                    .select(article)
                    .from(article)
                    .orderBy(article.id.desc())
                    .fetch();
        }

        // 키워드가 없거나, 검색타입이 hashTag가 아닌경우, 전체 게시물
        return jpaQueryFactory
                .select(article)
                .from(article)
                .orderBy(article.id.desc())
                .fetch();
    }
}
