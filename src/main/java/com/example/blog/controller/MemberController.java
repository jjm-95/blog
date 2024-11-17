package com.example.blog.controller;

import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import com.example.blog.domain.Post;
import com.example.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @GetMapping("/Main")
    public String memberList(Model model){
        List<Member> memberList = memberRepository.findAll();

        model.addAttribute("members",memberList);

        return "Main.html";
    }

    @GetMapping("/regist")
    public String registMember(Model model, Member member) {
        model.addAttribute("member", member);
        return "Regist.html";
    }

    @PostMapping("/regist")
    String addMember(@RequestParam("id") String id,
                     @RequestParam("password") String password,
                     @RequestParam("nickname") String nickname){
        memberService.saveMember(id, password, nickname);

        return "redirect:/Main";
    }

    @DeleteMapping("/deleteMember/{memberNum}")
    ResponseEntity<String> deleteMember(@PathVariable("memberNum") Long memberNum){
        memberRepository.deleteById(memberNum);
        System.out.println(memberNum + "is deleted");
        return ResponseEntity.status(200).body("삭제완료: "+ memberNum);
    }


}
