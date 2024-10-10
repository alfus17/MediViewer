package com.study.medi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
	
	@Value("${dicome.img.path}") // 변수 파일에 등록된 이미지 폴더 경로
	String envImgPath;
	
    @GetMapping("/")
    public String home(Model model ) {
        model.addAttribute("envImgPath", envImgPath);
        return "index";
    }
	
}
