package com.example.file.base;

import com.example.file.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {

    @Bean
    CommandLineRunner init(MemberService memberService, PasswordEncoder passwordEncoder) {

        return args -> {
            memberService.join("user1", "1234", "user1@test.com");
            memberService.join("user2", "1234", "user2@test.com");
        };

    }

}