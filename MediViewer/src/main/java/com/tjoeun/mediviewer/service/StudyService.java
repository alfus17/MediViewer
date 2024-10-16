package com.tjoeun.mediviewer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.ReqParams;
import com.tjoeun.mediviewer.domain.WorkList;
import com.tjoeun.mediviewer.repository.StudyRepository;

@Service
public class StudyService {
	
	@Autowired
	private StudyRepository studyRepo;
	
	final static int DEF_SLICE = 10;
	final static int DEF_NOW_PAGE = 0;
	
	final static String QUERY_CONNECTOR = " and ";
	final static String[] QUERY_FIELD = {"pID", "pName", "modality", "reportStatus", "sDate", "eDate"};
	
	public Pageable setPageable(Integer nowPage, Integer slice) {
		if(nowPage != null && slice != null) {
			Sort sort = Sort.by(Sort.Order.desc("studyDate"));
			Pageable pageable = PageRequest.of(nowPage - 1, slice, sort);
			return pageable; 			
		}
		
		Sort sort = Sort.by(Sort.Order.desc("studyDate"));
		Pageable pageable = PageRequest.of(DEF_NOW_PAGE, DEF_SLICE, sort);
		return pageable;
	}

	public Integer getAllStudyCount() {
		return studyRepo.findAll().size();
	}
	
	public HashMap<String, Object> getAllStudyTab(Integer nowPage, Integer slice) {
		HashMap<String, Object> result = new HashMap<>();
		
		Pageable pageable = setPageable(nowPage, slice);
		System.out.println(pageable);
		Page<WorkList> items = studyRepo.findBy(pageable);
		
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		return result;
	}
	
	public HashMap<String, Object> getQueryStudyTab(ReqParams params) {
		HashMap<String, Object> result = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		
		Pageable pageable = setPageable(params.getNowPage(), params.getSlice());
		System.out.println(pageable);
		
		if(params.getPID() != null && !(params.getPID()).equals("")) {
			sql.append(QUERY_FIELD[0]).append("=").append(params.getPID()).append(QUERY_CONNECTOR);
		}
		
		if(params.getPName() != null && !(params.getPName()).equals("")) {
			sql.append(QUERY_FIELD[1]).append("=").append(params.getPName()).append(QUERY_CONNECTOR);
		}
		
		if(params.getModality() != null && !(params.getModality()).equals("")) {
			sql.append(QUERY_FIELD[2]).append("=").append(params.getModality()).append(QUERY_CONNECTOR);
		}
		
		if(params.getStatus() != null && params.getStatus() != 0) {
			sql.append(QUERY_FIELD[3]).append("=").append(params.getStatus()).append(QUERY_CONNECTOR);
		}
		
		if(params.getSDate() != null && !(params.getSDate()).equals("")) {
			sql.append(QUERY_FIELD[4]).append("=").append(params.getSDate()).append(QUERY_CONNECTOR);
		}
		
		if(params.getEDate() != null && !(params.getEDate()).equals("")) {
			sql.append(QUERY_FIELD[5]).append("=").append(params.getEDate()).append(QUERY_CONNECTOR);
		}
		
		System.out.println(sql.toString());
		
		return null;
	}
}
