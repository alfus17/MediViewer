package com.tjoeun.mediviewer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tjoeun.mediviewer.domain.Member;
import com.tjoeun.mediviewer.service.MemberService;

@Controller
public class ViewController 
{
	private final MemberService memberService;

    @Autowired
    public ViewController(MemberService memberService) 
    {
        this.memberService = memberService;
    }
    
	@GetMapping("/") // 기본 URL에 대한 GET 요청 처리
	public String home()
	{
		return "/index"; // home.html 페이지 반환
	}
	
	@GetMapping("/login") // 로그인 URL에 대한 GET 요청 처리
	public String login()
	{
		return "/security/login"; // login.html 페이지 반환
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "/security/logout"; // logout.html 페이지 반환
	}
	
	@GetMapping("/signup")
    public String signupForm() 
    {
        return "/security/signup"; // signup.html 페이지 반환
    }
	
	@GetMapping("/userRolePage") // 사용자 권한에 따라 페이지 반환
    public String userRolePage(Model model) 
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) 
        {
            // 사용자가 ADMIN 권한을 가진 경우
            List<Member> members = memberService.getAllMembers();
            model.addAttribute("members", members); // Thymeleaf에서 사용할 수 있도록 모델에 추가
            return "/security/admin"; // admin.html 페이지 반환
        } 
        else 
        {
            // 사용자가 USER 권한을 가진 경우
            return "/security/user"; // user.html 페이지 반환
        }
    }
	
	// view 페이지
	@GetMapping("/view/{studykey}")
    public String showStudyView(@PathVariable("studykey") Long studykey) {      
        return "/view";
    }
	
}