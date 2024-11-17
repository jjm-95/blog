package com.example.blog.controller;

import com.example.blog.domain.Member;
import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    String post_write(Model model, Post post){
        model.addAttribute("post", post);
        log.trace("post_write invoked.");
        return "Post.html";
    }

    @PostMapping("/post")
    String addPost(@RequestParam("title") String title,
                     @RequestParam("content") String content,
                     @RequestParam("memberNum") Long memberNum){
        postService.savePost(title, content, memberNum);

        return "redirect:/Main";
    }

}
