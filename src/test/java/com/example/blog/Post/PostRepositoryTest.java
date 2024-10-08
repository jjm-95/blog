package com.example.blog.Post;


import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("포스트 불러오기")
    public void postLoad(){

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .author("jjm")
                .build());

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);


    } // postLoad

    @AfterEach // 모든 테스트 종료 후 자동 시행하는 코드
    public void cleanUp(){ // 테스트 종료 후 남은 데이터 정리
        postRepository.deleteAll();
    } // cleanUp

} // PostRepositoryTest
