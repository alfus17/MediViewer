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

import com.tjoeun.mediviewer.domain.DcmList;
import com.tjoeun.mediviewer.domain.ReqParams;
import com.tjoeun.mediviewer.domain.WorkList;
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
	
	@GetMapping
	public ResponseEntity<HashMap<String, Object>> getDefaultWorkList() {
		return ResponseEntity.ok().body(studyService.getAllStudyTab(null, null)); 
	}
	
	@PostMapping("/query")
	public ResponseEntity<HashMap<String, Object>> getQueryWorkList(@RequestBody ReqParams params) {
		System.out.println("ListController_getQueryWorkList_params : "+params);
		return ResponseEntity.ok().body(studyService.getQueryStudyTab(params)); 
	}
	
	@PostMapping("/histories")
	public ResponseEntity<Object> getQueryhistories(@RequestBody ReqParams params) {
		System.out.println("ListController_getQueryhistories_params : "+params);
		
		HashMap<String, Object> result = new HashMap<>();
		
		List<WorkList> historyWorkList = studyService.getHistoryList(params);
		List<DcmList> dcmList = imgTabService.getDcmByStudyKey(params);
		
		result.put("WorkList", historyWorkList);
		result.put("preview", dcmList);
		
		return ResponseEntity.ok().body(result); 
	}	
	
}
