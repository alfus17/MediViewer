package com.tjoeun.mediviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.domain.CommentTab;
import com.tjoeun.mediviewer.domain.req.ReqParams;
import com.tjoeun.mediviewer.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public CommentTab getCommentByStudyKey(ReqParams params) {
		System.out.println("CommentService_getCommentByStudyKey : " + params.getStudyKey());
		CommentTab result = null;
		Optional<CommentTab> comment = commentRepo.findById(params.getStudyKey());
		
		if(comment.isPresent()) {
			System.out.println("코멘트가 존재함 : " + comment.toString());
			result = comment.get();

		}else {
			System.out.println("코멘트가 존재하지 않음 ");
			result = null;
		}
		
		return result;
			
		
	}

}
