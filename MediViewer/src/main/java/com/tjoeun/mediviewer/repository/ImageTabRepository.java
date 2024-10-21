package com.tjoeun.mediviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.ImageTab;

import java.util.List;

@Repository
public interface ImageTabRepository extends JpaRepository<ImageTab, ImageTab.ImageId> {
    
    // studyKey와 seriesKey로 FNAME과 PATH 값을 가져오는 메소드
    List<ImageTab> findByIdStudyKeyAndIdSeriesKey(Long studyKey, Long seriesKey);
}
