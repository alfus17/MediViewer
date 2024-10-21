package com.tjoeun.mediviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tjoeun.mediviewer.domain.res.DcmList;
import com.tjoeun.mediviewer.service.ImageTabService;
import java.util.ArrayList;

@Controller
public class ViewController {

    @Autowired
    private ImageTabService imageTabService;

    // studykey와 serieskey를 이용해 FNAME Map과 PATH를 반환하는 API
    @GetMapping("/api/dcmList")
    public ResponseEntity<ArrayList<DcmList>> getDcmList(@RequestParam("studykey") Integer studyKey,
                                                         @RequestParam("serieskey") Long seriesKey) {
        ArrayList<DcmList> dcmList = imageTabService.getDcmListByStudyKey(studyKey, seriesKey);
        return ResponseEntity.ok(dcmList);
    }

    // view.html 페이지를 반환하는 메서드
    @GetMapping("/view")
    public String showView() {
    	
        return "view"; // src/main/resources/templates/view.html 파일을 반환
    }
}
