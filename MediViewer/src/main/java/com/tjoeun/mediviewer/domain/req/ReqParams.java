package com.tjoeun.mediviewer.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqParams {
	/*
	 * 	list request 객체
	 */
	private Long studyKey;
    private String pid;
    private String pname;
    private String modality;
    private Integer status;
    private Integer slice;
    private Integer nowPage;
    private String sdate;
    private String edate;
    private Long seriesKey;
    private String comment;
    
    
//    public ReqParams (String pID , String pName) {
//    	this.pID = pID;
//    	this.pName = pName;
//    }
    
}