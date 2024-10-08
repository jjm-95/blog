package com.example.blog.Member;

import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @AfterEach
    public void cleanUp() {
        // H2의 PK 인덱스 초기화 쿼리
        memberRepository.deleteAll(); // 먼저 데이터 삭제
        entityManager
                .createNativeQuery("ALTER TABLE MEMBER ALTER COLUMN member_Num RESTART WITH 1")
                .executeUpdate(); // 그 후 PK 인덱스 초기화
    }

    @Test
    @DisplayName("멤버 리포지토리 테스트")
    public void memberRepo (){
        String id = "Test1234";
        String password = "!Abc1234";
        String nickname = "테스트닉네임";

        Member member = Member.builder()
                .id(id)
                .password(password)
                .nickname(nickname).build();

        memberRepository.save(member);

        List<Member> memberList = memberRepository.findAll();

        Member memberAssert = memberList.get(0);
        assertThat(memberList.size()).isGreaterThan(0);
        assertThat(memberAssert.getId()).isEqualTo(id);
        assertThat(memberAssert.getPassword()).isEqualTo(password);
        assertThat(memberAssert.getNickname()).isEqualTo(nickname);
    }

}
