package com.tjoeun.mediviewer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.domain.res.DcmList;
import com.tjoeun.mediviewer.repository.ImageRepository;

@Service
public class ImageTabService {
	
	@Autowired
	private ImageRepository imageRepo;
	
	public Optional<DcmList> getPreviewByStudyKey(ReqParams params) {
		System.out.println("ImageTabService_getDcmByStudyKey_params : " + params);
		Optional<DcmList> dcmList = imageRepo.findPreviewByStudyKey(params.getStudyKey());
		System.out.println("ImageTabService_getDcmByStudyKey_dcmList : " + dcmList.toString());
		return dcmList;
	}
	
	// studyKey와 seriesKey로 DcmList를 조회
    public ArrayList<String> getDcmListByStudyAndSeriesKey(Long studyKey, Long seriesKey) {
        return imageRepo.findAllByStudyKey(studyKey, seriesKey);
    }

	
	public ArrayList<String> getImageByStudyKey(ReqParams params){
		System.out.println("ImageTabService_getImageByStudyKey_params : " + params);
		ArrayList<String> dcmList = imageRepo.findImagesByStudyKey(params.getStudyKey());
		System.out.println("ImageTabService_getImageByStudyKey_dcmList : " + dcmList.toString());
		return dcmList;
	}
    
//	public HashMap<String, Object> getSeriesObject(Long no) {
//		HashMap<String, Object> result = new HashMap<>();
//		
//		Long minSeries = imageRepo.findBy(no);
//		ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(no);
//		ArrayList<String> imageFileName = imageRepo.findAllByStudyKey(no, minSeries);
//		
//		result.put("minSeries", minSeries);
//		result.put("seriesList", seriesList);
//		result.put("imageFileName", imageFileName);
//		
//		return result;
//	}


	public HashMap<String, Object> getSeriesObject(Long studyKey) {
		System.out.println("ImageTabService_getDcmListByStudyKey_studyKey : " + studyKey);
		
		
		HashMap<String, Object> responseMap = new HashMap<>();
		
		Long minSeries = imageRepo.findBy(studyKey);
		System.out.println("ImageTabService_getDcmListByStudyKey_minSeries : " + minSeries);
		
		ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(studyKey);
		System.out.println("ImageTabService_getDcmListByStudyKey_seriesList : " + seriesList);

		ArrayList<String> imageFileName = imageRepo.findAllByStudyKey(studyKey, minSeries);
		System.out.println("ImageTabService_getDcmListByStudyKey_imageFileName : " + imageFileName);

		
		// result -> responseMap으로 수정
		responseMap.put("minSeries", minSeries);
		responseMap.put("seriesList", seriesList);
		responseMap.put("imageFileName", imageFileName);
		
		System.out.println("ImageTabService_getDcmListByStudyKey_responseMap : " + responseMap);

		return responseMap;
	}
	
	// 시리즈키 배열만 리턴 
	public ArrayList<Long> getSeriesKeyList(Long studyKey) {
		System.out.println("ImageTabService_getSeriesKeyList_studyKey : " + studyKey);
		
		ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(studyKey);
		System.out.println("ImageTabService_getSeriesKeyList_seriesList : " + seriesList);
		return seriesList;
	}
	
	
}
