package com.example.blog.Member;

import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import com.example.blog.service.MemberService;
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

    @Autowired
    MemberService memberService;

    @PersistenceContext
    private EntityManager entityManager;

    @AfterEach
    public void cleanUp() {

        memberRepository.deleteAll(); // 먼저 데이터 삭제
        // H2의 PK 인덱스 초기화 쿼리
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

    @Test
    @DisplayName("SaveMember Service 기능 테스트")
    public void memberSaveService(){
        String id = "dddd1234";
        String password = "!Abc12345";
        String nickname = "ServiceTest";

        memberService.saveMember(id, password, nickname);

        List<Member> memberList = memberRepository.findAll();

        Member memberAssert = memberList.get(0);
        assertThat(memberList.size()).isGreaterThan(0);
        assertThat(memberAssert.getId()).isEqualTo(id);
        assertThat(memberAssert.getPassword()).isEqualTo(password);
        assertThat(memberAssert.getNickname()).isEqualTo(nickname);
    }

    @Test
    @DisplayName("SaveMember Service 기능 fail 테스트 ")
    public void failedMemberSaveService(){
        String id1 = "dddd123";
        String password1 = "!Abc12345";
        String nickname1 = "ServiceTest";

        Member member1 = Member.builder()
                .id(id1)
                .password(password1)
                .nickname(nickname1).build();

        memberService.saveMember(member1.getId(), member1.getPassword(), member1.getNickname());

        String id2 = "dddd1234";
        String password2 = "!abc12345";
        String nickname2 = "ServiceTest";

        Member member2 = Member.builder()
                .id(id2)
                .password(password2)
                .nickname(nickname2).build();

        memberService.saveMember(member2.getId(), member2.getPassword(), member2.getNickname());

        String id3 = "dddd1234";
        String password3 = "!Abc12345";
        String nickname3 = "S";

        Member member3 = Member.builder()
                .id(id3)
                .password(password3)
                .nickname(nickname3).build();

        List<Member> memberList1 = memberRepository.findAll();


        memberService.saveMember(member3.getId(), member3.getPassword(), member3.getNickname());

        List<Member> memberList2 = memberRepository.findAll();

        int compareSize = memberList2.size() - memberList1.size();

        assertThat(compareSize).isEqualTo(0L);
    }

}
