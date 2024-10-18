package com.tjoeun.mediviewer.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.domain.res.DcmList;
import com.tjoeun.mediviewer.domain.res.WorkList;
import com.tjoeun.mediviewer.service.CommentService;
import com.tjoeun.mediviewer.service.ImageTabService;
import com.tjoeun.mediviewer.service.MemberService;
import com.tjoeun.mediviewer.service.PatientService;
import com.tjoeun.mediviewer.service.SeriesService;
import com.tjoeun.mediviewer.service.StudyService;


@Controller
@RequestMapping("/api/lists")
public class ListController {
	//
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ImageTabService imgTabService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private SeriesService seriesService;

	@Autowired
	private StudyService studyService;
	
	
	/**
	 * @return { 
	 * 				"reportStatus" : [판독상태 <Integer>],
	 * 				"modalities"   : [검사장비 종류 <String>],
	 * 				"count" 	   : 총 쿼리 갯수 <Integer>,
	 * 				"items"		   : <WorkList>
	 * 			}
	 */
	@GetMapping
	public ResponseEntity<HashMap<String, Object>> getDefaultWorkList() {
		return ResponseEntity.ok().body(studyService.getAllStudyTab(null, null)); 
	}
	
	/**
	 * @param <ReqParams> = { "pid" : <String>  ,"pname" : <String>  ,"modality" : <String>  ,"status" : <String>  ,"sdate" : <String>  ,"edate" : <String>  }
	 * @return HashMap<String, Object> = { "count" : <Integer>  , "items" : <WorkList> }
	 */
	@PostMapping("/query")
	public ResponseEntity<HashMap<String, Object>> getQueryWorkList(@RequestBody ReqParams params) {
		System.out.println("ListController_getQueryWorkList_params : "+params);
		return ResponseEntity.ok().body(studyService.getQueryStudyTab(params)); 
	}
	
	/**
	 * @param <ReqParams> = { "pid" : <String>  , "pname" : <String> , "studyKey" : <Integer>  } 
	 * @return HashMap<String, Object> = { "WorkList" : <WorkList>  , "preview" : <dcmList> }
	 */
	@PostMapping("/histories")
	public ResponseEntity<HashMap<String, Object>> getQueryhistories(@RequestBody ReqParams params) {
		System.out.println("ListController_getQueryhistories_params : "+params);
		
		// 결과값 반환 hashmap
		HashMap<String, Object> result = new HashMap<>();
		
		List<WorkList> historyWorkList = studyService.getHistoryList(params);
		List<DcmList> dcmList = imgTabService.getPreviewByStudyKey(params);
		
		result.put("WorkList", historyWorkList);
		result.put("preview", dcmList);
		
		return ResponseEntity.ok().body(result); 
	}
	
	/**
	 * @return ArrayList<DcmList>
	 */
	@GetMapping("/preview/{studykey}")
	public ResponseEntity< List<DcmList>> getPrivew(@PathVariable("studykey") Integer studyKey){
		// 로직 통일
		ReqParams params = new ReqParams();
		params.setStudyKey(studyKey);
		System.out.println("getPrivew_params : " + params);
		
		return ResponseEntity.ok().body(imgTabService.getPreviewByStudyKey(params));
	}
	
	
}
