package com.tjoeun.mediviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.ImageTab;
import com.tjoeun.mediviewer.domain.res.DcmList;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ImageTabRepository extends JpaRepository<ImageTab, ImageTab.ImageId> {
    
	  @Query(value ="SELECT it.PATH as path, it.FNAME as fname, it.IMAGEKEY as imageKey, it.SERIESKEY as seriesKey " +
              "FROM IMAGETAB it " +
              "WHERE it.STUDYKEY = :studyKey AND it.SERIESKEY = :seriesKey", 
              nativeQuery = true)
	  
	  ArrayList<DcmList> findByStudyKey(@Param("studyKey") Integer studyKey, 
			  							@Param("seriesKey") Long seriesKey);
}
