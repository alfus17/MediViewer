package com.tjoeun.mediviewer.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.mediviewer.repository.ImageRepository;
import com.tjoeun.mediviewer.repository.SeriesRepository;

@Service
public class SeriesService {
	
	@Autowired
	private SeriesRepository seriesRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	public ArrayList<Long> getSeriesList(Long no) {
		ArrayList<Long> result = seriesRepository.getByStudyKey(no);
		return result;
	}
	
}
