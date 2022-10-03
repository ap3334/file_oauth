package com.example.file.member.controller;

import com.example.file.member.entity.Member;
import com.example.file.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String showJoin() {

        return "member/join";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email, MultipartFile profileImg, HttpServletRequest req) {

        Member oldMember = memberService.getMemberByUsername(username);

        if (oldMember != null) {
            return "redirect:/?errorMsg=Already done";
        }

        Member member = memberService.join(username, password, email, profileImg);

        try {
            req.login(username, password);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/member/profile";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {

        Member loginedMember = memberService.getMemberByUsername(principal.getName());
        model.addAttribute("loginedMember", loginedMember);

        return "member/profile";

    }

    @GetMapping("/login")
    public String showLogin() {
        return "member/login";
    }

}
