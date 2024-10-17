package com.tjoeun.mediviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.DcmList;
import com.tjoeun.mediviewer.domain.ReqParams;
import com.tjoeun.mediviewer.repository.ImageRepository;
import com.tjoeun.mediviewer.repository.StudyRepository;

@Service
public class ImageTabService {
	
	@Autowired
	private ImageRepository imageRepo;
	
	public List<DcmList> getDcmByStudyKey(ReqParams params){
		System.out.println("ImageTabService_getDcmByStudyKey_params : " + params);
		List<DcmList> dcmList = imageRepo.findByStudyKey(params.getStudyKey(), 1l, 1l);
		System.out.println("ImageTabService_getDcmByStudyKey_dcmList : " + dcmList);
		return dcmList;
	}

}
