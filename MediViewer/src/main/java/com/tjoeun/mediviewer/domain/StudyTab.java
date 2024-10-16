package com.tjoeun.mediviewer.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="STUDYTAB")
public class StudyTab {

    @Id
    @Column(name = "STUDYKEY")
    private Integer studyKey;

    @Column(name = "STUDYINSUID", nullable = false, length = 255)
    private String studyInsUid;

    @Column(name = "ACCESSNUM", length = 255)
    private String accessNum;

    @Column(name = "STUDYDATE", length = 16)
    private String studyDate;

    @Column(name = "STUDYTIME", length = 16)
    private String studyTime;

    @Column(name = "STUDYID", length = 255)
    private String studyId;

    @Column(name = "STUDYDESC", length = 255)
    private String studyDesc;

    @Column(name = "MODALITY", length = 16)
    private String modality;

    @Column(name = "BODYPART", length = 255)
    private String bodyPart;

    @Column(name = "PATIENTKEY")
    private Long patientKey;

    @Column(name = "PID", length = 255)
    private String pid;

    @Column(name = "PNAME", length = 255)
    private String pName;

    @Column(name = "PSEX", length = 16)
    private String pSex;

    @Column(name = "PBIRTHDATETIME", length = 16)
    private String pBirthDateTime;

    @Column(name = "PATAGE", length = 16)
    private String patAge;

    @Column(name = "EXAMSTATUS")
    private Integer examStatus;

    @Column(name = "REPORTSTATUS")
    private Integer reportStatus;

    @Column(name = "SERIESCNT")
    private Integer seriesCnt;

    @Column(name = "IMAGECNT")
    private Integer imageCnt;

    @Column(name = "NONSERIESCOUNT")
    private Integer nonSeriesCount;

    @Column(name = "NONIMAGECOUNT")
    private Integer nonImageCount;

    @Column(name = "VERIFYFLAG")
    private Integer verifyFlag;

    @Column(name = "DEPT", length = 255)
    private String dept;

    @Column(name = "REFPHYSICIANNAME", length = 255)
    private String refPhysicianName;

    @Column(name = "OPERATORSNAME", length = 255)
    private String operatorsName;

    @Column(name = "INSNAME", length = 255)
    private String insName;

    @Column(name = "STATIONNAME", length = 255)
    private String stationName;

    @Column(name = "STEREOCOUNT")
    private Integer stereoCount;

    @Column(name = "PROTOCOLNAME", length = 255)
    private String protocolName;

    @Column(name = "VIEWPOSITION", length = 255)
    private String viewPosition;

    @Column(name = "LATERALITY", length = 255)
    private String laterality;

    @Column(name = "ARCHSTATUS")
    private Integer archStatus;

    @Column(name = "DELFLAG")
    private Integer delFlag;

    @Column(name = "BACKUPSTATUS")
    private Integer backupStatus;

    @Column(name = "MISMATCHFLAG")
    private Integer mismatchFlag;

    @Column(name = "READINGPRIORITY")
    private Integer readingPriority;

    @Column(name = "ABNORMALPATIENT")
    private Integer abnormalPatient;

    @Column(name = "INSERTDATE", length = 16)
    private String insertDate;

    @Column(name = "INSERTTIME", length = 16)
    private String insertTime;

    @Column(name = "HOSPITALID")
    private Long hospitalId;

    @Column(name = "BURNCNT")
    private Integer burnCnt;

    @Column(name = "VALIDATEFLAG")
    private Integer validateFlag;

    @Column(name = "REQREADSTATUS")
    private Integer reqReadStatus;

    @Column(name = "TBFLAG")
    private Integer tbFlag;

    @Column(name = "MOVIEFLAG")
    private Integer movieFlag;

    @Column(name = "OTHERSCPSENDSTATUS")
    private Integer otherScpSendStatus;

    @Column(name = "AETITLE", length = 255)
    private String aeTitle;

    @Column(name = "FILESIZE")
    private BigDecimal fileSize;

    @Column(name = "INSERTED", length = 255)
    private String inserted;

    @Column(name = "UPDATED", length = 255)
    private String updated;

    @Column(name = "AI_COMPANY", length = 255)
    private String aiCompany;

    @Column(name = "AI_MODEL_NAME", length = 255)
    private String aiModelName;

    @Column(name = "AI_UPDATED", length = 255)
    private String aiUpdated;

    @Column(name = "AI_SCORE")
    private Float aiScore;

    @Column(name = "AI_PRIORITY")
    private Integer aiPriority;

    @Column(name = "AI_NUMBER_OF_FINDINGS")
    private Integer aiNumberOfFindings;

    @Column(name = "AI_ABNORMAL_YN", length = 1)
    private String aiAbnormalYn;

    @Column(name = "AI_FINDING", length = 255)
    private String aiFinding;

    @Column(name = "AI_REPORT", length = 4000)
    private String aiReport;

    @Column(name = "AI_VERSION", length = 16)
    private String aiVersion;

    @Column(name = "STUDY_KEY")
    private Long studyKey1;

}
