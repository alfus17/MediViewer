package com.tjoeun.mediviewer.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.mediviewer.service.MemberService;

@RestController
public class MemberController implements CommandLineRunner {
										// CommandLineRunner는 스프링에서 제공하는 인터페이스, run 메소드를 실행시킴

    @Autowired
    private MemberService memberService;

    @GetMapping("/loadData") // 데이터 로드를 위한 엔드포인트 (클라이언트가 서버와 통신하기 위해 요청을 보내는 특정 url)
    public String loadData() {
        memberService.loadInitialData(); // MemberService의 loadInitialData 메소드를 호출
        return "데이터 로드 완료"; // 클라이언트에게 응답
    }
    
    @Override
    public void run(String... args) throws Exception {
        memberService.loadInitialData(); // 애플리케이션 시작 시 초기 데이터를 로드
        			// 로드하는 이유는 기본적인 설정이나 필수 데이터들을 미리 준비해두기 위해서
        			// 예를 들면 db에 admin이라는 유저가 있어야 로그인이 되기 때문에 없다면 생성을 해서 이를 미리 준비하는 것
    }
}
/*
	1. CommandLineRunner는 스프링에서 제공하는 인터페이스, run 메소드를 실행시킴
	2. run 메소드가 애플리케이션 시작 시 초기 데이터를 로드 
		로드하는 이유는 기본적인 설정이나 필수 데이터들을 미리 준비해두기 위해서
		예를 들면 db에 admin이라는 유저가 있어야 로그인이 되기 때문에 없다면 생성을 해서 이를 미리 준비하는 것
	3. loadInitialData가 MemberService의 loadInitialData 메소드를 호출 (여기서 MembserService로 이동)
*/