package com.tjoeun.mediviewer.container;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/view")
    public String showDetailPage() {
        // view.html을 templates 폴더에 두면 해당 파일을 반환
        return "view";
    }
}
