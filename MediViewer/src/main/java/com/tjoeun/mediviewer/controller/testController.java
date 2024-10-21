package com.tjoeun.mediviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    // /view URL 요청 처리
    @GetMapping("/")
    public String showViewPage() {
        return "index2"; // templates/view.html 파일을 반환
    }
}
