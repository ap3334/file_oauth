package com.example.file.app.base;

import com.example.file.app.article.entity.Article;
import com.example.file.app.article.service.ArticleService;
import com.example.file.app.member.entity.Member;
import com.example.file.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {

    @Bean
    CommandLineRunner init(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {
        return args -> {

            String password = passwordEncoder.encode("1234");

            Member member1 = memberService.join("user1", password, "user1@test.com");
            memberService.setProfileImgByUrl(member1, "https://picsum.photos/200/300");

            Member member2 = memberService.join("user2", password, "user2@test.com");
            memberService.setProfileImgByUrl(member2, "https://picsum.photos/200/300");

            Article article1 = articleService.write(member1, "제목1", "내용1", "#자바 #프로그래밍");
            articleService.addGenFileByUrl(article1, "common", "inBody", 1, "https://picsum.photos/200/300");
            articleService.addGenFileByUrl(article1, "common", "inBody", 2, "https://picsum.photos/200/300");
            articleService.addGenFileByUrl(article1, "common", "inBody", 3, "https://picsum.photos/200/300");
            articleService.addGenFileByUrl(article1, "common", "inBody", 4, "https://picsum.photos/200/300");

            Article article2 = articleService.write(member1, "제목2", "내용2", "#HTML #프로그래밍");
            articleService.addGenFileByUrl(article2, "common", "inBody", 1, "https://picsum.photos/200/300");
            articleService.addGenFileByUrl(article2, "common", "inBody", 2, "https://picsum.photos/200/300");

        };

    }

}