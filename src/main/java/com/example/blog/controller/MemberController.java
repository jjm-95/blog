package com.example.blog.controller;

import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/Main")
    public String memberList(Model model){
        List<Member> memberList = memberRepository.findAll();

        model.addAttribute("members",memberList);

        return "Main.html";
    }

}
