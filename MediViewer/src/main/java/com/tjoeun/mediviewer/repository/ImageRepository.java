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
	ArrayList<DcmList> findByStudyKey(@Param("studyKey")Long studyKey, @Param("seriesKey")Long seriesKey );

	// studyKey를 통해서 dcm 전부 쿼리 
	@Query(value = "SELECT replace('wadouri:/dcm/'||it.path||it.fname, '\', '/') as FNAME FROM ImageTab it WHERE it.STUDYKEY = :studyKey ORDER BY SERIESKEY ASC, IMAGEKEY ASC", nativeQuery = true)
	ArrayList<String> findImagesByStudyKey(@Param("studyKey")Integer studyKey  );

	

	@Query(value = "SELECT min(it.serieskey) FROM ImageTab it WHERE it.STUDYKEY = :studyKey", nativeQuery = true)
	Long findBy(@Param("studyKey") Long studyKey);
	
	@Query(value = "SELECT distinct it.serieskey FROM ImageTab it WHERE it.STUDYKEY = :studyKey order by 1", nativeQuery = true)
	ArrayList<Long> findAllSeriesByStudyKey(@Param("studyKey") Long studyKey);

	@Query(value = "SELECT distinct it.path FROM ImageTab it WHERE it.STUDYKEY = :studyKey and it.SERIESKEY = :seriesKey", nativeQuery = true)
	String findBy(@Param("studyKey") Long studyKey, @Param("seriesKey")Long minSeries);

	@Query(value = "select 'wadouri:/dcm/'||replace(replace(it.path, '\\\\', '/'), '\\', '/')||it.fname as FNAME from imagetab it where it.STUDYKEY = :studyKey order by it.SERIESKEY, curseqnum", nativeQuery = true)
	ArrayList<String> findAllByStudyKey(@Param("studyKey") Long no );
	
	@Query(value = "select 'wadouri:/dcm/'||replace(replace(it.path, '\\\\', '/'), '\\', '/')||it.fname as FNAME from imagetab it where it.STUDYKEY = :studyKey and it.SERIESKEY = :seriesKey order by curseqnum", nativeQuery = true)
	ArrayList<String> findAllByStudyKeyAndSeriesKey(@Param("studyKey") Long no, @Param("seriesKey") Long minSeries);

}