package com.tjoeun.mediviewer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjoeun.mediviewer.domain.Member;
import com.tjoeun.mediviewer.service.MemberService;

@Controller
public class LoginController 
{
	private final MemberService memberService;
	
	@Autowired
    public LoginController(MemberService memberService) 
    {
        this.memberService = memberService;
    }
	
	// 회원가입을 위한 부분
	@PostMapping("/signup")
    public String signup
    (
        @RequestParam("memberId") String memberId,
        @RequestParam("memberPwd") String memberPwd,
        @RequestParam("name") String name,
        @RequestParam("email") String email
    ) 
    {
    	String role = "USER"; // 일반 회원은 "USER" 역할로 생성
        memberService.createMember(memberId, memberPwd, name, email, role);
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
    }
    
    // 아이디 중복 확인을 위한 엔드포인트
    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkIdAvailability(@RequestParam("memberId") String memberId) 
    {
        boolean isAvailable = memberService.isMemberIdAvailable(memberId);
        return ResponseEntity.ok(isAvailable);
    }
	
	// 회원 삭제 요청 처리
	@PostMapping("/delete-member")
	public String deleteMember(@RequestParam("memberId") String memberId) 
	{
	    memberService.deleteMember(memberId);
	    return "redirect:/userRolePage"; // 삭제 후 관리자 페이지로 이동
	}
}