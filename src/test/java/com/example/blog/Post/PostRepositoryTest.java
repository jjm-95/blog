package com.example.blog.Post;


import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import com.example.blog.domain.Post;
import com.example.blog.domain.PostRepository;
import com.example.blog.dto.PostSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("글 포스트, 멤버num fk 테스트")
    public void postWithMemberNum(){
        String title = "테스트 게시글";
        String content = "테스트 본문";
        Long memberNum = 2L;
        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재 하지 않음" + memberNum));

        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build());

    }

    @Test
    @DisplayName("포스트 불러오기")
    public void postLoad(){

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        Long memberNum = 2L;
        Member member = memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재 하지 않음 memberNum is not exist -> " + memberNum));


        postRepository.save(PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .member(member)
                .build().toEntity());

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);

        System.out.println(post);


    } // postLoad

    @AfterEach // 모든 테스트 종료 후 자동 시행하는 코드
    public void cleanUp(){ // 테스트 종료 후 남은 데이터 정리
        postRepository.deleteAll();
    } // cleanUp

} // PostRepositoryTest
