package com.example.blog.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity // jpa의 어노테이션
public class Category {
    @Id // pk 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;

    @Column(length = 500, nullable = false) // 길이는 500, null 불허
    private String name;

}
