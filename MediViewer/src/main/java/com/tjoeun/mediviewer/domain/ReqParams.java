package com.tjoeun.mediviewer.domain;

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
	
    private String pID;
    private String pName;
    private String modality;
    private Integer state;
    private Integer slice;
    private Integer nowPage;
    private String sDate;
    private String eDate;
    
    
    public ReqParams (String pID , String pName) {
    	this.pID = pID;
    	this.pName = pName;
    }
    
}
