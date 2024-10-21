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
	
	public Optional<DcmList> getPreviewByStudyKey(ReqParams params){
		System.out.println("ImageTabService_getDcmByStudyKey_params : " + params);
		Optional<DcmList> dcmList = imageRepo.findPreviewByStudyKey(params.getStudyKey());
		System.out.println("ImageTabService_getDcmByStudyKey_dcmList : " + dcmList.toString());
		return dcmList;
	}
	
	 // studyKey와 seriesKey로 DcmList를 조회
    public ArrayList<DcmList> getDcmListByStudyKey(Integer studyKey, Long seriesKey) {
        return imageRepo.findByStudyKey(studyKey, seriesKey);
    }
	
	public ArrayList<String> getImageByStudyKey(ReqParams params){
		System.out.println("ImageTabService_getImageByStudyKey_params : " + params);
		ArrayList<String> dcmList = imageRepo.findImagesByStudyKey(params.getStudyKey());
		System.out.println("ImageTabService_getImageByStudyKey_dcmList : " + dcmList.toString());
		return dcmList;
	}
    
	public HashMap<String, Object> getSeriesObject(Long no) {
		HashMap<String, Object> result = new HashMap<>();
		
		Long minSeries = imageRepo.findBy(no);
		ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(no);
		ArrayList<String> imageFileName = imageRepo.findAllByStudyKey(no, minSeries);
		
		result.put("minSeries", minSeries);
		result.put("seriesList", seriesList);
		result.put("imageFileName", imageFileName);
		
		return result;
	}

}
