package com.example.blog.controller;

import com.example.blog.domain.PostRepository;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    String addPost(){
        postService.savePost("김진은 FuckUpMe", "Mood Developer", 2L);

        return "redirect:/Main";
    }

}
