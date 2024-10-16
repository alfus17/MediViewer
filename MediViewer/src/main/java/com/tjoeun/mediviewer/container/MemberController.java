package com.tjoeun.mediviewer.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.mediviewer.service.MemberService;

@RestController
public class MemberController implements CommandLineRunner {

    @Autowired
    private MemberService memberService;

    @GetMapping("/loadData") // 데이터 로드를 위한 엔드포인트
    public String loadData() {
        memberService.loadInitialData();
        return "데이터 로드 완료"; // 클라이언트에게 응답
    }
    
    @Override
    public void run(String... args) throws Exception {
        memberService.loadInitialData(); // 애플리케이션 시작 시 데이터 로드
    }
}
