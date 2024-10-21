package com.tjoeun.mediviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public Object getCommentByStudyKey(ReqParams params) {
		System.out.println("CommentService_getCommentByStudyKey : " + params);
		return null;
	}

}
