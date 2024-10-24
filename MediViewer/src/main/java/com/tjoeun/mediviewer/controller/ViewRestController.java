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
import java.util.List;

@Controller
@RequestMapping("/api/views")
public class ViewRestController {

    @Autowired
    private ImageTabService imageTabService;

    /*
    // URL에서 studykey를 path variable로 받아서 HashMap을 반환
    @GetMapping("/{studykey}")
    public ResponseEntity<HashMap<String, Object>> getSerieskeyList(@PathVariable("studykey") Long studykey) {
        System.out.println("ViewRestController_getSerieskeyList_studyKey : " + studykey);

        // studyKey에 따라 서비스에서 HashMap을 가져오는 메서드를 호출
        HashMap<String, Object> responseMap = imageTabService.getSeriesObject(studykey);
        System.out.println("ViewRestController_getSerieskeyList_responseMap : " + responseMap);

        return ResponseEntity.ok().body(responseMap);
    }
    */	
    
    /*
    @GetMapping("/{studykey}/{serieskey}")
    public ResponseEntity<HashMap<String, Object>> getSeriesByStudyAndSeriesKey(
            @PathVariable("studykey") Long studyKey,
            @PathVariable("serieskey") Long seriesKey) {

        System.out.println("ViewRestController_getSeriesByStudyAndSeriesKey_studyKey : " + studyKey);
        System.out.println("ViewRestController_getSeriesByStudyAndSeriesKey_seriesKey : " + seriesKey);

        // 서비스 호출하여 studyKey와 seriesKey에 맞는 결과를 받음
        HashMap<String, Object> responseMap = imageTabService.getSeries(studyKey, seriesKey);
        System.out.println("mmmmmmmmmmmmmmmm" + responseMap);

        // 만약 시리즈나 이미지 파일이 없을 경우 에러 응답 반환
        if (responseMap.isEmpty()) {
            return ResponseEntity.badRequest().body(responseMap);
        }

        // 정상 응답으로 HashMap 반환
        return ResponseEntity.ok(responseMap);
    }
    */
    
    
    @GetMapping("/{studykey}/all-images")
    public ResponseEntity<HashMap<String, Object>> getAllSeriesImageFiles(
            @PathVariable("studykey") Long studyKey) {

        System.out.println("ViewRestController_getAllSeriesImageFiles_studyKey : " + studyKey);

        // 서비스 호출하여 studyKey에 맞는 모든 시리즈 및 이미지 파일 이름을 가져옴
        HashMap<String, Object> responseMap = imageTabService.getAllSeriesImageFiles(studyKey);

        // 만약 시리즈나 이미지 파일이 없을 경우 에러 응답 반환
        if (responseMap.isEmpty()) {
            return ResponseEntity.badRequest().body(responseMap);
        }

        // 정상 응답으로 HashMap 반환
        return ResponseEntity.ok(responseMap);
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
