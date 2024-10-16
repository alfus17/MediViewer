package com.study.medi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.medi.domain.StudyTab;
import com.study.medi.domain.WorkList;


public interface StudyRepository extends JpaRepository<StudyTab, Integer> {

	Page<StudyTab> findAll(Pageable pageable);

}
