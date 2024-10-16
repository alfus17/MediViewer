package com.tjoeun.mediviewer.container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController 
{
	@GetMapping("/") // 기본 URL에 대한 GET 요청 처리
	public String home()
	{
		return "/page/home"; // home.html 페이지 반환
	}
	
	@GetMapping("/login") // 로그인 URL에 대한 GET 요청 처리
	public String login()
	{
		return "/page/login"; // login.html 페이지 반환
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "/page/logout"; // logout.html 페이지 반환
	}
	
	@GetMapping("/asd") // 테스트용
	public String asd()
	{
		return "/page/asd";
	}
}