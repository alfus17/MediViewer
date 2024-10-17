package com.tjoeun.mediviewer.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewController {

    @Autowired
    private ImageService imageService; // ImageService는 데이터 접근을 위한 서비스 클래스

    @GetMapping("/api/images")
    public List<ImageData> getImagesByStudyAndSeriesKey(
            @RequestParam("studykey") int studyKey,
            @RequestParam("serieskey") int seriesKey) {
        return imageService.findImagesByStudyAndSeriesKey(studyKey, seriesKey);
    }
}
