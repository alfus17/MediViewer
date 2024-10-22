package com.tjoeun.mediviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tjoeun.mediviewer.service.ImageTabService;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/api/views")
public class ViewRestController {

    @Autowired
    private ImageTabService imageTabService;

    // URL에서 studykey를 path variable로 받아서 HashMap을 반환
//    @GetMapping("/{studykey}")
//    public ResponseEntity<HashMap<String, Object>> getSerieskeyList(@PathVariable("studykey") Long studykey) {
//        System.out.println("ViewRestController_getSerieskeyList_studyKey : " + studykey);
//
//        // studyKey에 따라 서비스에서 HashMap을 가져오는 메서드를 호출
//        HashMap<String, Object> responseMap = imageTabService.getSeriesObject(studykey);
//        System.out.println("ViewRestController_getSerieskeyList_responseMap : " + responseMap);
//
//        return ResponseEntity.ok().body(responseMap);
//    }
//    
    
    public void PrintLog() {
    	
    }
    
    
    // URL에서 studykey를 path variable로 받아서 HashMap을 반환
    @GetMapping("/{studykey}")
    public ResponseEntity<ArrayList<Long>> getSerieskeyList(@PathVariable("studykey") Long studykey) {
        System.out.println("ViewRestController_getSerieskeyList_studyKey : " + studykey);

        ArrayList<Long> responseMap = imageTabService.getSeriesKeyList(studykey);
        System.out.println("ViewRestController_getSerieskeyList_responseMap : " + responseMap);

        return ResponseEntity.ok().body(responseMap);
    }
    
    
    // get {study}/{series} 를 받아서  해당 시리즈 dcm 경로 배열 출력
    @GetMapping("/{studykey}/{serieskey}")
    public ResponseEntity<ArrayList<String>> getDcmList(@PathVariable("studykey") Long studykey ,@PathVariable("serieskey") Long serieskey ) {
        System.out.println("ViewRestController_getDcmList_studykey : " + studykey);
        System.out.println("ViewRestController_getDcmList_serieskey : " + serieskey);
        
        ArrayList<String> QueryResult = imageTabService.getDcmListByStudyAndSeriesKey(studykey,serieskey);
        System.out.println("ViewRestController_getDcmList_QueryResult : " + QueryResult);

        return ResponseEntity.ok().body(QueryResult);
    }
    
    
}
