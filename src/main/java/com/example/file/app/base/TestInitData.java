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
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner init(MemberService memberService, ArticleService articleService, PasswordEncoder passwordEncoder) {

        return args -> {

            String password = passwordEncoder.encode("1234");

            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");
            Member member3 = memberService.join("user3", password, "user3@test.com");
            Member member4 = memberService.join("user4", password, "user4@test.com");

            Article article1 = articleService.write(member1, "제목1", "내용1", "#자바 #프로그래밍");
            Article article2 = articleService.write(member1, "제목2", "내용2", "#HTML #프로그래밍");

        };

    }

}