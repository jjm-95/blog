package com.example.blog.service;


import com.example.blog.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;



}
