package com.tjoeun.mediviewer.service;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.ReqParams;
import com.tjoeun.mediviewer.domain.WorkList;
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
	public HashMap<String, Object> findAllByParams(ReqParams params)  {
		// 결과값 리턴 데이터 
		HashMap<String, Object> result = new HashMap<>();
		StringBuilder sql  = new StringBuilder();
		
		// 필드 목록 가져오기 리플렉터 사용
		Field[] fields = params.getClass().getDeclaredFields();
		
		// sql문 만들기
		for(Field field : fields) {
			field.setAccessible(true);// 필드 접근권한 허용
//			System.out.println("service_findAllByParams : " + field.getName());
//			System.out.println("service_findAllByParams_val: " + field.get(params));
			
			try {
				if(field.get(params) != null && !(field.getName().equals("eDate"))  && !(field.getName().equals("sDate"))) {
					if(!(field.getName().equals("nowPage")) && !(field.getName().equals("slice"))) {
						sql.append(field.getName()).append(" = ").append( field.get(params)).append(SQL_CONNECTOR);
					}
				}
				
			} catch (Exception e) {
				System.out.println("error : " + e);
			}
		}
		sql.append(" 1 = 1");
		System.out.println("ervice_findAllByParams_sql : " + sql);
	
		// 기본 페이징 처리 세팅 
		Pageable pageable = setPageable(params.getNowPage(), params.getSlice());
		System.out.println(pageable);
		
		
		// 쿼리 
		Page<WorkList> items = studyRepo.findAllByParams(pageable , sql.toString());
		
		
		result.put("count", items.getTotalElements());
		result.put("items", items.getContent());
		return result;
	}
	
	
}
