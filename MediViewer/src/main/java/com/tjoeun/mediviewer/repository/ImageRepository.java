package com.tjoeun.mediviewer.repository;

import java.util.ArrayList;
import java.util.List;

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
	@Query(value ="SELECT * FROM ImageTab it where it.STUDYKEY = :studyKey and  it.SERIESKEY = :seriesKey and  it.IMAGEKEY = :imageKey" ,  nativeQuery = true)
	ArrayList<DcmList> findByStudyKey(@Param("studyKey")Integer studyKey, @Param("seriesKey")Long seriesKey , @Param("imageKey")Long imageKey);
	

	// studyKey를 통해서 dcm path 와 fname 쿼리 
	@Query(value ="SELECT * FROM ImageTab it where it.STUDYKEY = :studyKey and  it.SERIESKEY = :seriesKey " ,  nativeQuery = true)
	ArrayList<DcmList> findByStudyKey(@Param("studyKey")Integer studyKey, @Param("seriesKey")Long seriesKey );

}
