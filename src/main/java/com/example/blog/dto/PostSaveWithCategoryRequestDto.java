package com.example.blog.dto;

import com.example.blog.domain.Category;
import com.example.blog.domain.Member;
import com.example.blog.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveWithCategoryRequestDto {
    private String title;
    private String content;
    private Member member;
    private String author;
    private Category category;

    @Builder // Builder 패턴을 만들기 위한 어노테이션
    public PostSaveWithCategoryRequestDto(String title, String content, Member member, String author, Category category){
        this.title = title;
        this.content = content;
        this.member = member;
        this.author = author;
        this.category = category;
    } // Post

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .author(author).category(category)
                .build();
    }
}
