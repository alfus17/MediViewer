//package com.tjoeun.mediviewer.service;
//
//import com.tjoeun.mediviewer.domain.Member;
//import com.tjoeun.mediviewer.repository.MemberRepository;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomMemberDetailsService implements UserDetailsService {
//    
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByMemberId(username);
//        if (member == null) {
//            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(member.getMemberId(), member.getMemberPwd(), new ArrayList<>());
//        // org.springframework.security.core.userdetails.User 는 시큐리티에서 제공하는 User 클래스의 완전한 패키지 경로
//        // ArrayList는 현재 권한 리스트가 비어있지만 사용자의 권한을 담을 수 있게 하기 위해서 쓰임
//    }
//}
