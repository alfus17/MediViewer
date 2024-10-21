package com.tjoeun.mediviewer.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.mediviewer.domain.ImageTab;
import com.tjoeun.mediviewer.service.ImageTabService;

import java.util.List;
import java.util.Map;

@RestController
public class ViewController {

    @Autowired
    private ImageTabService imageTabService;

    // studykey와 serieskey를 이용해 FNAME Map과 PATH를 반환하는 API
    @GetMapping("/api/images")
    public ResponseEntity<List<ImageTab>> getImageDetails(@RequestParam("studykey") Long studyKey,
                                                               @RequestParam("serieskey") Long seriesKey) {
        // 서비스에서 FNAME Map과 PATH를 가져옴
    	List<ImageTab> result = imageTabService.getFnameMapAndPath(studyKey, seriesKey);

        // 조회된 데이터를 ResponseEntity로 반환 (JSON 형식)
        return ResponseEntity.ok(result);
    }
}
