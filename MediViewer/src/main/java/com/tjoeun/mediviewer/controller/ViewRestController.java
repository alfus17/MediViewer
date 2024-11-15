package com.tjoeun.mediviewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.mediviewer.domain.CommentTab;
import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.repository.CommentRepository;
import com.tjoeun.mediviewer.service.CommentService;
import com.tjoeun.mediviewer.service.ImageTabService;

import java.util.*;
import java.util.Optional;  // Optional 클래스 임포트


@Controller
@RequestMapping("/api/views")
public class ViewRestController {

    @Autowired
    private ImageTabService imageTabService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentRepository commentRepository;  // CommentRepository 주입

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
    
    @PostMapping("/saveComment")
    public ResponseEntity<HashMap<String, Object>> saveComment(@RequestBody Map<String, Object> requestData) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            // studyKey 추출 및 유효성 검사
            String studyKeyStr = (String) requestData.get("studykey");
            if (studyKeyStr == null || studyKeyStr.isEmpty()) {
                throw new IllegalArgumentException("studyKey is missing or empty");
            }
            Long studyKey = Long.parseLong(studyKeyStr);

            // commentText 추출 및 유효성 검사
            String commentText = (String) requestData.get("comment");
            if (commentText == null) {
                throw new IllegalArgumentException("Comment is missing");
            }

            System.out.println("받은 studyKey: " + studyKey);
            System.out.println("받은 commentText: " + commentText);

            // ReqParams 객체 생성
            ReqParams params = new ReqParams();
            params.setStudyKey(studyKey);
            params.setComment(commentText);

            // 기존 코멘트 조회
            Optional<CommentTab> existingComment = commentRepository.findByStudyKey(studyKey);

            if (existingComment.isPresent()) {
                // 기존 코멘트가 있으면 수정
                CommentTab commentToUpdate = existingComment.get();
                commentToUpdate.setComment(commentText);
                commentRepository.save(commentToUpdate);  // 수정된 코멘트를 저장
                response.put("success", true);
                response.put("message", "코멘트가 수정되었습니다.");
                System.out.println("기존 코멘트가 수정되었습니다: " + commentText);
            } else {
                // 기존 코멘트가 없으면 새로 저장
                CommentTab newComment = new CommentTab();
                newComment.setStudyKey(studyKey);
                newComment.setComment(commentText);
                commentRepository.save(newComment);  // 새로운 코멘트를 저장
                response.put("success", true);
                response.put("message", "새로운 코멘트가 저장되었습니다.");
                System.out.println("새로운 코멘트가 저장되었습니다: " + commentText);
            }
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            System.out.println("입력 오류: " + e.getMessage());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "코멘트 저장 중 오류 발생");
            System.out.println("코멘트 저장 중 오류 발생: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/comment/{studykey}")
    public ResponseEntity<HashMap<String, Object>> getCommentByStudyKey(@PathVariable("studykey") Long studyKey) {
        HashMap<String, Object> result = new HashMap<>();
        
        // ReqParams 객체 생성하고 studyKey 설정
        ReqParams params = new ReqParams();
        params.setStudyKey(studyKey);  // studyKey를 파라미터에 설정

        // 코멘트 조회
        CommentTab comment = commentService.getCommentByStudyKey(params);
        
        if (comment != null) {
            result.put("comment", comment.getComment());
        } else {
            result.put("comment", null);
        }

        return ResponseEntity.ok(result);
    }


}
