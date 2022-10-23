package com.example.file.app.article.service;

import com.example.file.app.article.entity.Article;
import com.example.file.app.article.repository.ArticleRepository;
import com.example.file.app.gen.entity.GenFile;
import com.example.file.app.gen.service.GenFileService;
import com.example.file.app.hashTag.entity.HashTag;
import com.example.file.app.hashTag.service.HashTagService;
import com.example.file.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final GenFileService genFileService;

    private final HashTagService hashTagService;

    public Article write(Long authorId, String subject, String content) {

        return write(new Member(authorId), subject, content);
    }

    public Article write(Long authorId, String subject, String content, String hashTagContents) {
        return write(new Member(authorId), subject, content, hashTagContents);
    }


    public Article write(Member author, String subject, String content) {
        return write(author, subject, content, "");
    }

    public Article write(Member author, String subject, String content, String hashTagContents) {

        Article article = Article
                .builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        hashTagService.applyHashTags(article, hashTagContents);

        return article;
    }

    public Article getArticleById(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public void addGenFileByUrl(Article article, String typeCode, String type2Code, int fileNo, String url) {
        genFileService.addGenFileByUrl("article", article.getId(), typeCode, type2Code, fileNo, url);
    }

    public Article getForPrintArticleById(Long id) {
        Article article = getArticleById(id);

        Map<String, GenFile> genFileMap = genFileService.getRelGenFileMap(article);
        List<HashTag> hashTags = hashTagService.getHashTags(article);

        article.getExtra().put("hashTags", hashTags);
        article.getExtra().put("genFileMap", genFileMap);

        return article;

    }

    public void modify(Article article, String subject, String content, String hashTagContents) {

        article.setSubject(subject);
        article.setContent(content);
        articleRepository.save(article);

        hashTagService.applyHashTags(article, hashTagContents);

    }

    public List<Article> getArticles() {
        return articleRepository.getQslArticlesOrderByIdDesc();
    }

    public List<Article> search(String kwType, String kw) {
        return articleRepository.searchQsl(kwType, kw);
    }

}

