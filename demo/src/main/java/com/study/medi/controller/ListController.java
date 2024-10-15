package com.study.medi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.medi.domain.StudyTab;
import com.study.medi.domain.WorkList;
import com.study.medi.service.CommentService;
import com.study.medi.service.ImageTabService;
import com.study.medi.service.MemberService;
import com.study.medi.service.PatientService;
import com.study.medi.service.SeriesService;
import com.study.medi.service.StudyService;


@Controller
@RequestMapping("/api")
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
	
	
	@PostMapping("/lists/query")
	public String getAllWorkList(@RequestBody HashMap<String, Object> params , Model model){
		// 파라미터 받기
       String pID = (String) params.get("pID");
       String pName = (String) params.get("pName");
       String modality = (String) params.get("modality");
       String state = (String) params.get("state");
       int slice = Integer.parseInt((String) params.get("slice"));
       int nowPage = Integer.parseInt((String) params.get("nowpage"));
       String [] date = (String[]) params.get("date");
       
       Sort sort = Sort.by(Sort.Order.desc("inserted"));
       Pageable pageable = PageRequest.of(nowPage - 1, slice, sort);
              
       System.out.println("result : "+studyService.getAllStudyTab(pageable));
       
       return "index";
		
	}
	
	
}
