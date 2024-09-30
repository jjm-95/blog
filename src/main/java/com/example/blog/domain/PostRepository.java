package com.example.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Jpa로 저장소를 설정, Post의 도메인의 pk의 타입인 Long으로 상속 받는다.

}
