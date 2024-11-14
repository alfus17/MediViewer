package com.tjoeun.mediviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tjoeun.mediviewer.domain.CommentTab;
import java.util.Optional;  // Optional을 임포트

@Repository
public interface CommentRepository extends JpaRepository<CommentTab, Long> {

	// studyKey로 Comment 조회, Optional<CommentTab> 반환
    Optional<CommentTab> findByStudyKey(Long studyKey);
}
