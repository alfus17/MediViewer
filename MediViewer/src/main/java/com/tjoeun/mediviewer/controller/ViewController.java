package com.tjoeun.mediviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController 
{
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
	
	@GetMapping("/asd") // 테스트용
	public String asd()
	{
		return "/security/test";
	}
	
	// view 페이지
	@GetMapping("/view/{studykey}")
    public String showStudyView(@PathVariable("studykey") Long studykey) {      
        return "view";
    }
}