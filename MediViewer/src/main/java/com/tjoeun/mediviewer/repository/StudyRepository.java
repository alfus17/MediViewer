package com.tjoeun.mediviewer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.StudyTab;
import com.tjoeun.mediviewer.domain.WorkList;

@Repository
public interface StudyRepository extends JpaRepository<StudyTab, Integer> {

	@Query(value = "select st.PID as pid, st.PNAME as pname, st.STUDYKEY as studykey, st.MODALITY as modality, st.STUDYKEY as studyKey, st.STUDYDESC as studyDesc, st.STUDYDATE as studyDate, st.REPORTSTATUS as reportStatus, st.SERIESCNT as seriesCnt, st.IMAGECNT as imageCnt from STUDYTAB st", nativeQuery = true)
	Page<WorkList> findBy(Pageable pageable);

}
