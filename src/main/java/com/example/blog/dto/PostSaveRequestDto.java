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

    @Builder
    public PostSaveRequestDto(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Post toEntity(){
        return Post.builder().title(title).content(content).member(member)
                .build();
    }


}
