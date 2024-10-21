package com.tjoeun.mediviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.ImageTab;
import com.tjoeun.mediviewer.repository.ImageTabRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageTabService {

    @Autowired
    private ImageTabRepository imageTabRepository;

    // studyKey와 seriesKey로 FNAME을 Map으로, PATH를 하나만 가져오는 메소드
//    public Map<String, Object> getFnameMapAndPath(Long studyKey, Long seriesKey) {
//        List<ImageTab> imageTabs = imageTabRepository.findByIdStudyKeyAndIdSeriesKey(studyKey, seriesKey);
////
////        // FNAME을 저장할 Map (key는 IMAGEKEY)
////        Map<Long, String> fnameMap = new HashMap<>();
////        
////        // 모든 레코드의 FNAME을 Map에 저장
////        String path = null;
////        for (ImageTab imageTab : imageTabs) {
////            fnameMap.put(imageTab.getId().getImageKey(), imageTab.getFname());
////            // PATH는 모든 레코드에서 동일하므로 첫 번째 값만 사용
////            if (path == null) {
////                path = imageTab.getPath();
////            }
////        }
////
////        // FNAME Map과 PATH를 반환할 수 있도록 Map으로 통합
////        Map<String, Object> result = new HashMap<>();
////        result.put("fnameMap", fnameMap);
////        result.put("path", path);
//
//        return result;
//    }
    
    
    
    public  List<ImageTab> getFnameMapAndPath(Long studyKey, Long seriesKey) {
        List<ImageTab> imageTabs = imageTabRepository.findByIdStudyKeyAndIdSeriesKey(studyKey, seriesKey);


        return imageTabs;
    }
}
