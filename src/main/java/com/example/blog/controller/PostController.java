package com.example.blog.controller;

import com.example.blog.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;



}
