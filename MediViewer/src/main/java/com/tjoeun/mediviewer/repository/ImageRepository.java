package com.tjoeun.mediviewer.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.ImageTab;
import com.tjoeun.mediviewer.domain.StudyTab;
import com.tjoeun.mediviewer.domain.res.DcmList;

@Repository
public interface ImageRepository extends JpaRepository<ImageTab, Long> {

	/*
	 * 
	 * @param studyKey
	 * @param seriesKey
	 * @param imageKey
	 * @return
	 */
	
	
	
	@Query(value ="SELECT * FROM ( SELECT * FROM ImageTab it  WHERE it.STUDYKEY = :studyKey ORDER BY SERIESKEY, IMAGEKEY) WHERE ROWNUM = 1" ,  nativeQuery = true)
	Optional<DcmList> findPreviewByStudyKey(@Param("studyKey")Integer studyKey);
	

	// studyKey를 통해서 dcm path 와 fname 쿼리 
	@Query(value ="SELECT * FROM ImageTab it where it.STUDYKEY = :studyKey and  it.SERIESKEY = :seriesKey " ,  nativeQuery = true)
	ArrayList<DcmList> findByStudyKey(@Param("studyKey")Integer studyKey, @Param("seriesKey")Long seriesKey );

	// studyKey를 통해서 dcm 전부 쿼리 
//	@Query(value = "SELECT * FROM ImageTab it WHERE it.STUDYKEY = :studyKey ORDER BY SERIESKEY ASC, IMAGEKEY ASC", nativeQuery = true)
//	ArrayList<ImageTab> findImagesByStudyKey(@Param("studyKey")Integer studyKey  );
	
}
