package com.tjoeun.mediviewer.container;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.mediviewer.domain.ReqParams;
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
		System.out.println(params);
		return ResponseEntity.ok().body(studyService.getQueryStudyTab(params)); 
	}	
	
}
