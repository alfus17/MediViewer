package com.tjoeun.mediviewer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.StudyTab;
import com.tjoeun.mediviewer.domain.WorkList;
import com.tjoeun.mediviewer.repository.StudyRepository;

@Service
public class StudyService {
	
	@Autowired
	private StudyRepository studyRepo;
	
	final static int DEF_SLICE = 10;
	final static int DEF_NOW_PAGE = 0;
	
	public Pageable setPageable(Integer nowPage, Integer slice) {
		if(nowPage != null && slice != null) {
			Sort sort = Sort.by(Sort.Order.desc("inserted"));
			Pageable pageable = PageRequest.of(nowPage - 1, slice, sort);
			return pageable; 			
		}
		
		Sort sort = Sort.by(Sort.Order.desc("inserted"));
		Pageable pageable = PageRequest.of(DEF_NOW_PAGE, DEF_SLICE, sort);
		return pageable;
	}

	public Integer getAllStudyCount() {
		return studyRepo.findAll().size();
	}
	
	public HashMap<String, Object> getAllStudyTab(Integer nowPage, Integer slice) {
		HashMap<String, Object> result = new HashMap<>();
		
		Integer count = getAllStudyCount();
		List<WorkList> items = studyRepo.findBy();
		
		result.put("count", count);
		result.put("items", items);
		
		return result;
	}
}
