package com.example.blog.dto;

import com.example.blog.domain.Member;
import com.example.blog.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private Member member;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, String content, Member member, String author){
        this.title = title;
        this.content = content;
        this.member = member;
        this.author = author;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .author(author)
                .build();
    }


}
