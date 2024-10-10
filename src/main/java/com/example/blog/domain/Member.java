package com.example.blog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
public class Member {

    @Id // pk 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙
    private Long memberNum;

    @Column(length = 30, nullable = false, unique = true)
    @Size(min = 5, message = "ID는 최소 8자 이상이어야 합니다.")
    private String id;

    @Column(length = 50, nullable = false)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "패스워드는 8자 이상, 대문자, 소문자, 숫자, 특수 문자를 포함해야 합니다."
    )
    @Size(min = 8, message = "패스워드는 최소 8자 이상이어야 합니다.")
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    private String nickname;


    @Pattern(
            regexp = "^[가-힣]{2,4}(?:\\s[가-힣]{2,4})?$",
            message = "이름은 2자에서 4자까지의 한글로 이루어져야 하며, 성과 이름 사이에 공백이 있을 수 있습니다."
    )
    private String name;

    @Pattern(
            regexp = "^[0-9]{10,11}$",
            message = "입력값은 숫자만 포함되어야 하며, 10자에서 11자 사이여야 합니다."
    )
    private String phoneNum;

    @Temporal(TemporalType.TIMESTAMP) 
    @Column(updatable = false)
    private LocalDateTime registDate; // 가입시 한번만 적용, 업데이트 불가

    // 엔티티가 처음 저장될 때 자동으로 실행
    @PrePersist
    protected void onCreate(){
        this.registDate = LocalDateTime.now(); // 현재 시간을 등록
    }

    private LocalDateTime lastLoginDate;

    public void updateLastLoginDate(){
        this.lastLoginDate = LocalDateTime.now();
    } // 로그인 시 로그인 일자 업데이트를 위한 메소드 작성

    @Builder // Builder 패턴을 만들기 위한 어노테이션
    public Member(String id, String password, String nickname){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    } // Member Builder

} // Member
