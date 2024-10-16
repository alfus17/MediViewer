package com.tjoeun.mediviewer.domain;

public interface WorkList {
	String getPid();
	String getPname();
	Integer getStudyKey();
	String getModality();
	String getStudyDesc();
	String getStudyDate();
	String getReportStatus();
	Integer getSeriesCnt();
	Integer getImageCnt();
}
 