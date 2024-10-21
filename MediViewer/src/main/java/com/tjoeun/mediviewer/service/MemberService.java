package com.tjoeun.mediviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;

import com.tjoeun.mediviewer.domain.Member;
import com.tjoeun.mediviewer.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService, CommandLineRunner 
{

    private final MemberRepository memberRepository; // Repository를 이용해 CRUD
    private final PasswordEncoder passwordEncoder; // db에서 비밀번호 암호화
    
	// 생성자를 통한 의존성 주입으로 리포지토리와 비밀번호 인코더를 주입
    @Autowired 
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) 
    {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
	// 사용자 정보를 불러오는 부분
    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        Member member = memberRepository.findByMemberId(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다 : " + username));

        return User.withUsername(member.getMemberId())
            .password(member.getMemberPwd())
            .roles("USER") // 사용자에게 "USER" 권한 부여
            .build();
    }

    // 사용자 생성하는 부분
    public Member createMember(String memberId, String memberPwd) 
    {
    	// 새로운 사용자 생성
        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberPwd(passwordEncoder.encode(memberPwd));
        return memberRepository.save(member);
    }
    
    // 실행 시 기본적인 설정이나 필수 데이터들을 세팅하는 부분
    @Override 
    public void run(String... args) throws Exception 
    {
        String ID = "admin"; // 사용할 아이디 입력
        String PW = "admin"; // 사용할 비밀번호 입력
        
        if (memberRepository.findByMemberId(ID).isEmpty()) 
        {
            createMember(ID, PW);
            System.out.println("회원이 성공적으로 생성되었습니다.");
        } 
        else 
        {
            System.out.println(ID + "는 이미 존재하는 아이디입니다.");
        }
    }
}