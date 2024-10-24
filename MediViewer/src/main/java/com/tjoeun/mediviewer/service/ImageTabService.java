package com.tjoeun.mediviewer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ArrayList<DcmList> getDcmListByStudyKey(Long studyKey, Long seriesKey) {
        return imageRepo.findByStudyKey(studyKey, seriesKey);
    }
    
    /*
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
	*/
	
		public HashMap<String, Object> getSeries(Long studyKey,Long seriesKey) {
			System.out.println("ImageTabService_getDcmListByStudyKey_studyKey : " + studyKey);				
			HashMap<String, Object> responseMap = new HashMap<>();
			
			
			ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(studyKey);
			System.out.println("ImageTabService_getDcmListByStudyKey_seriesList : " + seriesList);

			ArrayList<String> imageFileName = imageRepo.findAllByStudyKeySeriesKey(studyKey,seriesKey);
			System.out.println("ImageTabService_getDcmListByStudyKey_imageFileName : " + imageFileName);

			
			// result -> responseMap으로 수정
			responseMap.put("seriesList", seriesList);
			responseMap.put("imageFileName", imageFileName);
			
			System.out.println("ImageTabService_getDcmListByStudyKey_responseMap : " + responseMap);

			
			
			return responseMap;
		}
		
		
		public HashMap<String, Object> getAllSeriesImageFiles(Long studyKey) {
		    System.out.println("ImageTabService_getAllSeriesImageFiles_studyKey : " + studyKey);

		    // 반환할 결과 맵 생성
		    HashMap<String, Object> responseMap = new HashMap<>();

		    // 해당 studyKey에 포함된 모든 시리즈 리스트 조회
		    ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(studyKey);
		    System.out.println("ImageTabService_getAllSeriesImageFiles_seriesList : " + seriesList);

		    // 시리즈별 이미지 파일 리스트를 담을 리스트
		    List<List<String>> allImageFileNames = new ArrayList<>();

		    // 각 시리즈에 대해 이미지 파일 리스트 조회
		    for (Long seriesKey : seriesList) {
		        ArrayList<String> imageFileName = imageRepo.findAllByStudyKeySeriesKey(studyKey, seriesKey);
		        allImageFileNames.add(imageFileName);
		    }

		    // 결과를 responseMap에 추가
		    responseMap.put("imageFileNames", allImageFileNames);

		    System.out.println("ImageTabService_getAllSeriesImageFiles_responseMap : " + responseMap);

		    return responseMap;
		}

		// 시리즈키 배열만 리턴 
		public ArrayList<Long> getSeriesKeyList(Long studyKey) {
			System.out.println("ImageTabService_getSeriesKeyList_studyKey : " + studyKey);
			
			ArrayList<Long> seriesList = imageRepo.findAllSeriesByStudyKey(studyKey);
			System.out.println("ImageTabService_getSeriesKeyList_seriesList : " + seriesList);
			return seriesList;
		}
		
		 public ArrayList<String> getDcmListByStudyAndSeriesKey(Long studyKey, Long seriesKey) {
		        return imageRepo.findAllByStudyKey(studyKey, seriesKey);
		    }
}
