package com.tjoeun.mediviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.CommentTab;

@Repository
public interface CommentRepository extends  JpaRepository<CommentTab, Long>{

	
	
}
