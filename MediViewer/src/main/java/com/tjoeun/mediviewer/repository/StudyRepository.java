package com.tjoeun.mediviewer.repository;

import java.util.List;

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

	@Query(value = "select * from STUDYTAB st", nativeQuery = true)
	Page<WorkList> findBy(Pageable pageable);
	
	@Query(value = "select * from STUDYTAB st "
			+ "where (:pID IS NULL OR :pID = '' OR st.PID = :pID) "
	        + "and (:pName IS NULL OR :pName = '' OR st.PNAME = :pName) "
	        + "and (:modality IS NULL OR :modality = '' OR st.MODALITY = :modality) "
	        + "and (:reportStatus IS NULL OR st.REPORTSTATUS = :reportStatus) "
	        + "and (:sDate IS NULL OR st.STUDYDATE between :sDate and :eDate) "
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
	
	@Query(value ="SELECT DISTINCT st.MODALITY FROM StudyTab st" ,  nativeQuery = true)
	List<String> findModality();
	
	@Query(value ="SELECT DISTINCT st.REPORTSTATUS FROM StudyTab st" ,  nativeQuery = true)
	List<Integer> findReportStatus();


}
