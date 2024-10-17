package com.tjoeun.mediviewer.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.mediviewer.domain.ReqParams;
import com.tjoeun.mediviewer.domain.StudyTab;
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
	

//	@GetMapping("/query")
//	public ResponseEntity<HashMap<String, Object>> getQueryWorkList(@Param("pID") String pID, @Param("pName") String pName){
//		
//		HashMap<String , Object> reqParams =  new HashMap<>();
//		
//		reqParams.put("pID",pID);
//		reqParams.get("pName");
//		reqParams.get("modality");
//		reqParams.get("state");
//		reqParams.get("startDate");
//		reqParams.get("endDate");
//		
//		return ResponseEntity.ok().body(studyService.findAllByParams(null, null,reqParams)); 
//	}
//	
	
	
	@PostMapping("/query")
	public String getAllWorkList(@RequestBody ReqParams params , Model model){
		System.out.println("ListController_ReqParams : " + params);
		studyService.findAllByParams(params);
       return "index";
		
	}
	
	
}
