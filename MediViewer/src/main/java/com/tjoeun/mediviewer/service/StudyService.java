package com.tjoeun.mediviewer.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.WorkList;
import com.tjoeun.mediviewer.repository.StudyRepository;

@Service
public class StudyService {
	
	@Autowired
	private StudyRepository studyRepo;
	
	final static int DEF_SLICE = 10;
	final static int DEF_NOW_PAGE = 0;
	
	//  Pageable 세팅 
	public Pageable setPageable(Integer nowPage, Integer slice) {
		if(nowPage != null && slice != null) {
			Sort sort = Sort.by(Sort.Order.desc("studyDate"));
			Pageable pageable = PageRequest.of(nowPage - 1, slice, sort);
			return pageable; 			
		}
		
		Sort sort = Sort.by(Sort.Order.desc("inserted"));
		Pageable pageable = PageRequest.of(DEF_NOW_PAGE, DEF_SLICE, sort);
		return pageable;
	}
	
	//  모든 studytab 갯수 반환
	public Integer getAllStudyCount() {
		return studyRepo.findAll().size();
	}
	
	// 전부 쿼리 페이징으로 
	public HashMap<String, Object> getAllStudyTab(Integer nowPage, Integer slice) {
		HashMap<String, Object> result = new HashMap<>();
		
		Pageable pageable = setPageable(nowPage, slice);
		System.out.println(pageable);
		Page<WorkList> items = studyRepo.findBy(pageable);
		
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		return result;
	}
	
	// 전부 쿼리 파라미터로 검색데이터 가져오기 
	public HashMap<String, Object> findAllByParams(Integer nowPage, Integer slice , HashMap<String , Object> reqParams ) {
		// 결과값 리턴 데이터 
		HashMap<String, Object> result = new HashMap<>();
		String pid = (String) reqParams.get("pID");
		String pName = (String) reqParams.get("pName");
		String modality = (String) reqParams.get("modality");
		Integer state = Integer.parseInt((String) reqParams.get("state"));
		String startDate = (String) reqParams.get("startDate");
		String endDate = (String) reqParams.get("endDate");
		
		
		
		// 기본 페이징 처리 세팅 
		Pageable pageable = setPageable(nowPage, slice);
		System.out.println(pageable);
		
		
		// 쿼리 
		Page<WorkList> items = studyRepo.findAllByParams(pageable, pid, pName, modality, state, startDate, endDate);
		
		
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		return result;
	}
	
	
}
