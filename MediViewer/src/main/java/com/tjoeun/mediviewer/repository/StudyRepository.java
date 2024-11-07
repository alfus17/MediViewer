package com.tjoeun.mediviewer.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.StudyTab;
import com.tjoeun.mediviewer.domain.res.WorkList;

@Repository
public interface StudyRepository extends JpaRepository<StudyTab, Long> {
	
	// studytab 데이터 전부 쿼리
	@Query(value = "select * from STUDYTAB st", nativeQuery = true)
	Page<WorkList> findBy(Pageable pageable);
	
	// 검색 파라미터에 따른 동적 쿼리 
	@Query(value = "select * from STUDYTAB st "
			+ "where (:pID IS NULL OR st.PID = :pID) "
			+ "and (:pName IS NULL OR st.PNAME = :pName) "
			+ "and (:modality IS NULL OR st.MODALITY = :modality) "
			+ "and (:reportStatus IS NULL OR st.REPORTSTATUS = :reportStatus) "
			+ "and (:sDate IS NULL OR TO_DATE(st.STUDYDATE, 'YYYYMMDD') BETWEEN TO_DATE(:sDate, 'YYYY-MM-DD') AND TO_DATE(:eDate, 'YYYY-MM-DD'))  "
			+ "and 1=1",
		nativeQuery = true)
	Page<WorkList> findByDynamicQuery(
			@Param("pID") String pID,
            @Param("pName") String pName,
            @Param("modality") String modality,
            @Param("reportStatus") Integer status,
            @Param("sDate") String sDate,
            @Param("eDate") String eDate,
            Pageable pageable);
	
	// 검사장비 목록 쿼리 
	@Query(value ="SELECT DISTINCT st.MODALITY FROM StudyTab st" ,  nativeQuery = true)
	ArrayList<String> findModality();

	// 검사결과 상태 목록 쿼리 
	@Query(value ="SELECT DISTINCT st.REPORTSTATUS FROM StudyTab st" ,  nativeQuery = true)
	ArrayList<Integer> findReportStatus();

	//pid와 PName으로 과거이력 리스트 반환
	@Query(value ="SELECT * FROM StudyTab st where st.PID = :pID and  st.PNAME = :pName" ,  nativeQuery = true)
	ArrayList<WorkList> findByPidAndPName(@Param("pID") String pID,@Param("pName") String pName);

	
	
}
