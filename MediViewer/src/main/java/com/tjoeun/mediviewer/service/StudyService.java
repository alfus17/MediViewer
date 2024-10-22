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

import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.domain.res.WorkList;
import com.tjoeun.mediviewer.repository.StudyRepository;

import aj.org.objectweb.asm.commons.Method;

@Service
public class StudyService {
	
	@Autowired
	private StudyRepository studyRepo;
	
	final static int DEF_SLICE = 10;
	final static int DEF_NOW_PAGE = 0;
	final static String SQL_CONNECTOR = " and ";
	
	//  Pageable 세팅 
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
	
	//  모든 studytab 갯수 반환
	public Integer getAllStudyCount() {
		return studyRepo.findAll().size();
	}
	
	public HashMap<String, Object> getAllStudyTab(Integer nowPage, Integer slice) {
		HashMap<String, Object> result = new HashMap<>();
		
		Pageable pageable = setPageable(nowPage, slice);
		System.out.println(pageable);
		Page<WorkList> items = studyRepo.findBy(pageable);
		
		// modalities 가져오기
		ArrayList<String> modalityList = studyRepo.findModality();
		System.out.println("Service_modalityList : " + modalityList);
		
		// reportStatus 가져오기 
		ArrayList<Integer> reportStatusList = studyRepo.findReportStatus();
		System.out.println("Service_reportStatusList : " + reportStatusList);
		
		result.put("reportStatus", reportStatusList);
		result.put("modalities", modalityList);
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		
		return result;
	}
	

	public HashMap<String, Object> getQueryStudyTab(ReqParams params) {
		HashMap<String, Object> result = new HashMap<>();
		
		System.out.println(params);
		
		Pageable pageable = setPageable(params.getNowPage(), params.getSlice());
		Page<WorkList> items = studyRepo.findByDynamicQuery(
				params.getPid(),
				params.getPname(),
				params.getModality(),
				params.getStatus(),
				params.getSdate(),
				params.getEdate(),
				pageable);
		
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		return result;
	}
	
	// pid와 pname으로 과거 검사 이력 검색 하기 
	public ArrayList<WorkList> getHistoryList(ReqParams params){
		ArrayList<WorkList> historyWorkList = studyRepo.findByPidAndPName(params.getPid(), params.getPname());
		System.out.println("StudyService_getHistoryList_historyWorkList : "+ historyWorkList);
		
		return historyWorkList;
	}
}
