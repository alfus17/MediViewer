package com.study.medi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.medi.domain.StudyTab;
import com.study.medi.domain.WorkList;
import com.study.medi.repository.StudyRepository;

@Service
public class StudyService {
	
	@Autowired
	private StudyRepository studyRepo;

	// 전부 쿼리 페이징으로 
	public List<StudyTab> getAllStudyTab(Pageable pageable) {
		List<WorkList> response = new ArrayList<>();
//		for(StudyTab studytab : ) {
//			WorkList wlist = studytab;
//			response.add(wlist); 
//		}
		
		
		return studyRepo.findAll(pageable).getContent();
	}

}
