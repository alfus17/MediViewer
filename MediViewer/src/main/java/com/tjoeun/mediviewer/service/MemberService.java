package com.tjoeun.mediviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.Member;
import com.tjoeun.mediviewer.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void loadInitialData() {
        List<Member> members = memberRepository.findAll(); // DB에서 모든 사용자 조회
        String newMemberId = "admin"; // 새로 생성하려는 사용자 이름
        
        // 중복 검사
        boolean exists = members.stream()
                                .anyMatch(member -> member.getMemberId().equals(newMemberId));

        if (!exists) { // 만약 존재하지 않는다면 유저 생성
            // 유저 생성
            Member admin = new Member();
            admin.setMemberId(newMemberId); // 아이디
            admin.setMemberPwd(passwordEncoder.encode("admin")); // 비밀번호
            memberRepository.save(admin);
            System.out.println("사용자 생성됨: " + newMemberId);
        } else {
            System.out.println();
            System.out.println("이미 존재하는 사용자 이름입니다: " + newMemberId);
            System.out.println();
        }
    }
}
