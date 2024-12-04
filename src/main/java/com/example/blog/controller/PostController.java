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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/Main")
    public String showPostList(Model model) {
        List<Post> postList = postService.getPosts();

        if (postList == null) {
            postList = new ArrayList<>(); // null 방지
        }

        List<Post> sortedPosts = postList.stream().sorted((a, b) -> b.getId().compareTo(a.getId())) // id 내림차순
                .toList();
        model.addAttribute("postList",sortedPosts);

        return "Main"; // 템플릿 이름
    }

    @GetMapping("/post")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post()); // Post 객체를 추가
        model.addAttribute("member", new Member());

        return "Post"; // 템플릿 이름
    }


    @PostMapping("/post")
    String addPost(@RequestParam("title") String title,
                     @RequestParam("content") String content,
                     @RequestParam("memberNum") Long memberNum){
        postService.savePost(title, content, memberNum);

        return "redirect:/Main";
    }

}
