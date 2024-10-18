package com.tjoeun.mediviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.domain.res.DcmList;
import com.tjoeun.mediviewer.repository.ImageRepository;
import com.tjoeun.mediviewer.repository.StudyRepository;

@Service
public class ImageTabService {
	
	@Autowired
	private ImageRepository imageRepo;
	
	public ArrayList<DcmList> getPreviewByStudyKey(ReqParams params){
		System.out.println("ImageTabService_getDcmByStudyKey_params : " + params);
		ArrayList<DcmList> dcmList = imageRepo.findByStudyKey(params.getStudyKey(), 1l, 1l);
		System.out.println("ImageTabService_getDcmByStudyKey_dcmList : " + dcmList.toString());
		return dcmList;
	}

}
