package com.tjoeun.mediviewer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.StudyTab;
import com.tjoeun.mediviewer.domain.WorkList;

@Repository
public interface StudyRepository extends JpaRepository<StudyTab, Integer> {

	@Query(value = "select st.PID as pid, st.PNAME as pname, st.STUDYKEY as studykey, st.MODALITY as modality, st.STUDYKEY as studyKey, st.STUDYDESC as studyDesc, st.STUDYDATE as studyDate, st.REPORTSTATUS as reportStatus, st.SERIESCNT as seriesCnt, st.IMAGECNT as imageCnt from STUDYTAB st", nativeQuery = true)
	Page<WorkList> findBy(Pageable pageable);
	
	
	// jpql 동적 쿼리 
	@Query(value = "select "
	        + "st.PID as pid, "
	        + "st.PNAME as pName, "
	        + "st.STUDYKEY as studyKey, "
	        + "st.MODALITY as modality, "
	        + "st.STUDYDESC as studyDesc, "
	        + "st.STUDYDATE as studyDate, "
	        + "st.REPORTSTATUS as reportStatus, "
	        + "st.SERIESCNT as seriesCnt, "
	        + "st.IMAGECNT as imageCnt, "
	        + "st.FILESIZE as fileSize, "
	        + "st.INSERTED as inserted, "
	        + "st.UPDATED as updated, "
	        + "st.AI_SCORE as aiScore, "
	        + "st.AI_NUMBER_OF_FINDINGS as aiNumberOfFindings, "
	        + "st.AI_REPORT as aiReport "
	        + "from STUDYTAB st "
	        + "where (:pID IS NULL OR st.PID = :pID) "
	        + "and (:pName IS NULL OR st.PNAME LIKE %:pName%) "
	        + "and (:modality IS NULL OR st.MODALITY = :modality) "
	        + "and (:state IS NULL OR st.REPORTSTATUS = :state) "
	        + "and (:startDate IS NULL OR :endDate IS NULL OR st.STUDYDATE BETWEEN :startDate AND :endDate)")
	Page<WorkList> findAllByParams(Pageable pageable,
	                               @Param("pID") String pID,
	                               @Param("pName") String pName,
	                               @Param("modality") String modality,
	                               @Param("state") Integer state, // Integer 자료형 사용
	                               @Param("startDate") String startDate,
	                               @Param("endDate") String endDate);



}
