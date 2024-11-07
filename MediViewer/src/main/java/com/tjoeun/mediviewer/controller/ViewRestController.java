package com.tjoeun.mediviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.mediviewer.domain.CommentTab;
import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.service.CommentService;
import com.tjoeun.mediviewer.service.ImageTabService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/views")
public class ViewRestController {

    @Autowired
    private ImageTabService imageTabService;
    
    @Autowired
    private CommentService commentService;

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
        ReqParams params = new ReqParams();
        params.setStudyKey(studyKey);

        // 서비스 호출하여 studyKey에 맞는 모든 시리즈 및 이미지 파일 이름을 가져옴
        HashMap<String, Object> responseMap = imageTabService.getAllSeriesImageFiles(params);

        // 만약 시리즈나 이미지 파일이 없을 경우 에러 응답 반환
        if (responseMap.isEmpty()) {
            return ResponseEntity.badRequest().body(responseMap);
        }

        // 정상 응답으로 HashMap 반환
        return ResponseEntity.ok(responseMap);
    }
	
 // 시리즈키 반환 
    @GetMapping("/{studykey}")
    public ResponseEntity<HashMap<String, Object>> getSerieskeyList(@PathVariable("studykey") Long studykey) {
        System.out.println("ViewRestController_getSerieskeyList_studyKey : " + studykey);
        ReqParams params = new ReqParams();
        params.setStudyKey(studykey);
        HashMap<String, Object> result = new HashMap<>();
        
        
        // 시리즈 리스트 가져오기
        ArrayList<Long> seriesList = imageTabService.getSeriesKeyList(params);
        result.put("series", seriesList);
        System.out.println("ViewRestController_getSerieskeyList_responseMap : " + seriesList);
        
        // 스터디키로 코맨트 가져오기 
        CommentTab  comment = commentService.getCommentByStudyKey(params);
        result.put("comment", comment);
        System.out.println("ViewRestController_getSerieskeyList_comment : " + comment);

        return ResponseEntity.ok().body(result);
    }
    
 // get {study}/{series} 를 받아서  해당 시리즈 dcm 경로 배열 출력
    @GetMapping("/{studykey}/{serieskey}")
    public ResponseEntity<ArrayList<String>> getDcmList(@PathVariable("studykey") Long studykey ,@PathVariable("serieskey") Long serieskey ) {
        System.out.println("ViewRestController_getDcmList_studykey : " + studykey);
        System.out.println("ViewRestController_getDcmList_serieskey : " + serieskey);
        ReqParams params = new ReqParams();
        params.setStudyKey(studykey);
        params.setSeriesKey(serieskey);
        
        ArrayList<String> QueryResult = imageTabService.getDcmListByStudyAndSeriesKey(params);
        System.out.println("ViewRestController_getDcmList_QueryResult : " + QueryResult);

        return ResponseEntity.ok().body(QueryResult);
    }
}
