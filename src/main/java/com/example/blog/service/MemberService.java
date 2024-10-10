package com.example.blog.service;

import com.example.blog.domain.Member;
import com.example.blog.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void saveMember(String id, String password, String nickname){
        boolean idFlag = false;
        boolean passwordFlag = false;
        boolean nickNameFlag = false;


        Member member = new Member();

        if(id.length() <= 30 && id.length() >= 8){
            idFlag = true;
            member.setId(id);
            System.out.println("ID가 설정되었습니다.");
        }else {
            System.out.println("ID의 길이는 8자 이상, 30자 이하여야 합니다.");
        } // if for Id

        if(password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")){
            passwordFlag = true;
            member.setPassword(password);
            System.out.println("password 설정되었습니다.");
        }else {
            System.out.println("비번이 양식이 이상함");
        } // if for Password


        if(nickname.length() <= 30 && nickname.length() >=2){
            nickNameFlag = true;
            member.setNickname(nickname);
            System.out.println("nickName 설정되었습니다.");
        }else {
            System.out.println("nickName 양식이 이상함");
        } // if for nickName

        if (idFlag && passwordFlag && nickNameFlag){
            memberRepository.save(member);
            System.out.println("계정이 설정되었습니다.");
        } else{
            System.out.println("계정 설정을 실패했습니다.");
        }
        // save the Member

    }

}
