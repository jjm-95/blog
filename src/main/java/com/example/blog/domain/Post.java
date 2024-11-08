package com.example.blog.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity // jpa의 어노테이션
public class Post {
    
    @Id // pk 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;

    @Column(length = 500, nullable = false) // 길이는 500, null 불허
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)// Text로 정의, null 불허
    private String content;


    @Builder // Builder 패턴을 만들기 위한 어노테이션
    public Post(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.member = member;
    } // Post


}
