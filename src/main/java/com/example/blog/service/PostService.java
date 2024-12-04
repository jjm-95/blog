package com.example.blog.service;


import com.example.blog.domain.*;
import com.example.blog.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void savePost(String title, String content, Long memberNum){

        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("글 작성 실패: 작성자가 존재하지 않습니다. memberNum is not exist -> " + memberNum));

        String author = member.getNickname();
        postRepository.save(PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .member(member)
                .author(author)
                .build().toEntity());

        System.out.println("글작성 완료 :"+title+", "+ content + ", "+member.getNickname());

    }

    public void savePost(String title, String content, Long memberNum, Long categoryId){

        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("글 작성 실패: 작성자가 존재하지 않습니다. memberNum is not exist -> " + memberNum));

        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("글 작성 실패: 해당 카테고리가 존재 하지 않습니다. categoryId is not exist -> " + categoryId));

        String author = member.getNickname();
        String categoryName = category.getName();
        postRepository.save(PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .member(member)
                .author(author)
                .build().toEntity());

        System.out.println("글작성 완료 :"+title+", "+ content + ", "+member.getNickname());

    }

    public List<Post> getPosts(){

        return postRepository.findAll();

    } // getPosts


}
