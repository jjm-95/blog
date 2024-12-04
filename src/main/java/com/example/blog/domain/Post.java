package com.example.blog.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(length = 500, nullable = false) // 길이는 500, null 불허
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)// Text로 정의, null 불허
    private String content;

    @Column(length = 30, nullable = false, unique = true)
    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    private String author;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "categoryNum")
    private Category category;

    @Builder // Builder 패턴을 만들기 위한 어노테이션
    public Post(String title, String content, Member member, String author){
        this.title = title;
        this.content = content;
        this.member = member;
        this.author = author;
    } // Post

    @Builder // Builder 패턴을 만들기 위한 어노테이션
    public Post(String title, String content, Member member, String author, Category category){
        this.title = title;
        this.content = content;
        this.member = member;
        this.author = author;
        this.category = category;
    } // Post

}
