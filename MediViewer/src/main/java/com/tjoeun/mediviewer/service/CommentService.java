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
	    CommentTab result = commentRepo.findById(params.getStudyKey()).orElse(null); // 코멘트가 없으면 null 반환
	    if (result != null) {
	        System.out.println("코멘트가 존재함 : " + result);
	    } else {
	        System.out.println("코멘트가 존재하지 않음");
	    }
	    return result;
	}


	public void saveComment(ReqParams params) {
        CommentTab comment = new CommentTab();
        comment.setStudyKey(params.getStudyKey());
        comment.setComment(params.getComment());

        System.out.println("Comment 객체 생성: " + comment.toString());

        commentRepo.save(comment);  // 데이터베이스에 코멘트 저장
        System.out.println("코멘트가 데이터베이스에 저장되었습니다.");
    }
	    


}