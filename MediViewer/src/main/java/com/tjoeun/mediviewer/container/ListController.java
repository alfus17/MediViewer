package com.tjoeun.mediviewer.container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    // /view URL 요청 처리
    @GetMapping("/")
    public String showViewPage() {
        return "index2"; // templates/view.html 파일을 반환
    }
}
