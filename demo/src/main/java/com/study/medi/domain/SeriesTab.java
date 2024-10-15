package com.study.medi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SeriesTab {
	
	// 중복  pk 등록
	@Embeddable
    @Data
    public static class SeriesId implements Serializable {
        @Column(name = "STUDYKEY")
        private Long studyKey;

        @Column(name = "SERIESKEY")
        private Long seriesKey;

        // Default constructor required for JPA
        public SeriesId() {}

        public SeriesId(Long studyKey, Long seriesKey) {
            this.studyKey = studyKey;
            this.seriesKey = seriesKey;
        }
    }
	
	// pk 설정 
    @EmbeddedId
    private SeriesId id;

    @Column(name = "STUDYINSUID", length = 255)
    private String studyInsUid;

    @Column(name = "SERIESINSUID", length = 255)
    private String seriesInsUid;

    @Column(name = "SERIESNUM")
    private Integer seriesNum;

    @Column(name = "SERIESCURSEQNUM")
    private Integer seriesCurSeqNum;

    @Column(name = "STUDYID", length = 255)
    private String studyId;

    @Column(name = "MODALITY", length = 16)
    private String modality;

    @Column(name = "BODYPART", length = 255)
    private String bodyPart;

    @Column(name = "SERIESDESC", length = 255)
    private String seriesDesc;

    @Column(name = "PROTOCOLNAME", length = 255)
    private String protocolName;

    @Column(name = "VIEWPOSITION", length = 255)
    private String viewPosition;

    @Column(name = "LATERALITY", length = 255)
    private String laterality;

    @Column(name = "IMAGECNT")
    private Integer imageCnt;

    @Column(name = "VERIFYFLAG")
    private Integer verifyFlag;

    @Column(name = "DELFLAG")
    private Integer delFlag;

    @Column(name = "SERIESDATE", length = 255)
    private String seriesDate;

    @Column(name = "SERIESTIME", length = 255)
    private String seriesTime;

    @Column(name = "INSERTDATE", length = 255)
    private String insertDate;

    @Column(name = "INSERTTIME", length = 255)
    private String insertTime;

    @Column(name = "HOSPITALID")
    private Integer hospitalId;

    @Column(name = "PERFORMINGPHYSICIANNAME", length = 255)
    private String performingPhysicianName;

    @Column(name = "OPERATORSNAME", length = 255)
    private String operatorsName;

    @Column(name = "PATPOSITION", length = 255)
    private String patPosition;

    @Column(name = "MANUFACTURER", length = 255)
    private String manufacturer;

    @Column(name = "INSTITUTIONNAME", length = 255)
    private String institutionName;

    @Column(name = "STATIONNAME", length = 255)
    private String stationName;

    @Column(name = "INSTITUTIONALDEPARTMENTNAME", length = 255)
    private String institutionalDepartmentName;

    @Column(name = "MANUMODELNAME", length = 255)
    private String manuModelName;

    @Column(name = "NONIMAGECOUNT")
    private Integer nonImageCount;

    @Column(name = "FILESIZE")
    private Integer fileSize;

    @Column(name = "INSERTED", length = 255)
    private String inserted;

    @Column(name = "UPDATED", length = 255)
    private String updated;
    

	
	
}
