package com.example.blog.Category;

import com.example.blog.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("카테고리 입력 테스트")
    public void setCategoryTest() {
        String name = "음식";
        Member categoryMember;
        Long memberNum = 1L;

        memberRepository.findById(memberNum).orElseThrow(() ->
                new IllegalArgumentException("글 쓰기 실패: 해당 회원이 존재 하지 않음" + memberNum));

        categoryMember = new Member();
        categoryMember.setMemberNum(memberNum);

        Category category = new Category();
        category.setName(name);
        category.setMember(categoryMember);

        categoryRepository.save(category);

    } //setCategoryTest

    @Test
    @DisplayName("카테고리 삭제 테스트")
    public void deleteCategoryTest(){

        setCategoryTest();

        List<Category> categoryList1 = categoryRepository.findAll();
        Category category = categoryList1.get(0);
        categoryRepository.deleteById(category.getCategoryId());

        List<Category> categoryList2 = categoryRepository.findAll();

        int compareSize = categoryList2.size() - categoryList1.size();

        assertThat(compareSize).isEqualTo(-1L);

    } // deleteCategoryTest

    @AfterEach // 모든 테스트 종료 후 자동 시행하는 코드
    public void cleanUp(){ // 테스트 종료 후 남은 데이터 정리
        categoryRepository.deleteAll();
    } // cleanUp

} // CategoryRepositoryTest
