package com.study.medi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
//public class testController {
//	
//	@Value("${dicome.img.path}") // 변수 파일에 등록된 이미지 폴더 경로
//	String envImgPath;
//	
//	
//	// test로 루트 페이지에 환경변수값 리턴 해주는 코드  
//	//	필요 없을경우 주석 처리 후 진행 바람
//    @GetMapping("/")
//    public String home(Model model ) {
//        model.addAttribute("envImgPath", envImgPath);
//        return "index";
//    }
//	
//}
