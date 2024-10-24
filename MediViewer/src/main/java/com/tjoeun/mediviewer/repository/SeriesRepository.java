package com.tjoeun.mediviewer.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.SeriesTab;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesTab, Long>{

	@Query(value = "select distinct st.SERIESKEY from IMAGETAB st where STUDYKEY = :no", nativeQuery = true)
	ArrayList<Long> getByStudyKey(@Param("no") Long no);

}
