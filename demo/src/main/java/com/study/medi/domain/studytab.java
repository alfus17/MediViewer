package com.study.medi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class studytab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDYKEY")
    private int studyKey;

    @Column(name = "STUDYINSUID", length = 64, nullable = false)
    private String studyInsUid;

    @Column(name = "PATKEY", length = 64)
    private String patKey;

    @Column(name = "ACCESSNUM", length = 64)
    private String accessNum;

    @Column(name = "STUDYDATE", length = 16)
    private String studyDate;

    @Column(name = "STUDYTIME", length = 14)
    private String studyTime;

    @Column(name = "STUDYID", length = 64)
    private String studyId;

    @Column(name = "EXAMCODE", length = 64)
    private String examCode;

    @Column(name = "STUDYDESC", length = 256)
    private String studyDesc;

    @Column(name = "MODALITY", length = 16)
    private String modality;

    @Column(name = "BODYPART", length = 256)
    private String bodyPart;

    @Column(name = "PATIENTKEY")
    private int patientKey;

    @Column(name = "PID", length = 64)
    private String pid;

    @Column(name = "PNAME", length = 64)
    private String pName;

    @Column(name = "PLASTNAME", length = 64)
    private String pLastName;

    @Column(name = "PFIRSTNAME", length = 64)
    private String pFirstName;

    @Column(name = "PKNAME", length = 64)
    private String pKName;

    @Column(name = "PENAME", length = 64)
    private String pEName;

    @Column(name = "PSEX", length = 16)
    private String pSex;

    @Column(name = "PBIRTHDATETIME", length = 16)
    private String pBirthDateTime;

    @Column(name = "PATAGE", length = 16)
    private String patAge;

    @Column(name = "EXAMSTATUS", columnDefinition = "int default 0")
    private int examStatus;

    @Column(name = "REPORTSTATUS", columnDefinition = "int default 0")
    private int reportStatus;

    @Column(name = "SERIESCNT", columnDefinition = "int default 0")
    private int seriesCnt;

    @Column(name = "SERIESMOVIECNT", columnDefinition = "int default 0")
    private int seriesMovieCnt;

    @Column(name = "IMAGECNT", columnDefinition = "int default 0")
    private int imageCnt;

    @Column(name = "MOVIECNT", columnDefinition = "int default 0")
    private int movieCnt;

    @Column(name = "NONSERIESCOUNT", columnDefinition = "int default 0")
    private int nonSeriesCount;

    @Column(name = "NONIMAGECOUNT", columnDefinition = "int default 0")
    private int nonImageCount;

    @Column(name = "VERIFYFLAG", columnDefinition = "int default 0")
    private int verifyFlag;

    @Column(name = "VERIFYDATETIME", length = 14)
    private String verifyDateTime;

    @Column(name = "DEPT", length = 64)
    private String dept;

    @Column(name = "REFPHYSICIANNAME", length = 64)
    private String refPhysicianName;

    @Column(name = "REQPHYSICIANNAME", length = 64)
    private String reqPhysicianName;

    @Column(name = "PERFPHYSICIANNAME", length = 64)
    private String perfPhysicianName;

    @Column(name = "OPERATORSNAME", length = 64)
    private String operatorsName;

    @Column(name = "REFERTODRID", length = 64)
    private String referToDrId;

    @Column(name = "REFERTODRNAME", length = 64)
    private String referToDrName;

    @Column(name = "PATKIND", length = 1)
    private char patKind;

    @Column(name = "WARD", length = 64)
    private String ward;

    @Column(name = "SICKNAME", length = 256)
    private String sickName;

    @Column(name = "ADDEDINFO", length = 256)
    private String addedInfo;

    @Column(name = "HISCOMMENTS", length = 256)
    private String hisComments;

    @Column(name = "HISADDEDINFO1", length = 256)
    private String hisAddedInfo1;

    @Column(name = "HISADDEDINFO2", length = 256)
    private String hisAddedInfo2;

    @Column(name = "HISADDEDINFO3", length = 256)
    private String hisAddedInfo3;

    @Column(name = "INSNAME", length = 64)
    private String insName;

    @Column(name = "STATIONNAME", length = 64)
    private String stationName;

    @Column(name = "CONFIRMDATETIME", length = 16)
    private String confirmDateTime;

    @Column(name = "READINGDATETIME", length = 16)
    private String readingDateTime;

    @Column(name = "TRANSCRIBEDDATETIME", length = 16)
    private String transcribedDateTime;

    @Column(name = "READTYPE", columnDefinition = "int default 0")
    private int readType;

    @Column(name = "READCODE", length = 64)
    private String readCode;

    @Column(name = "READCODEDESC", length = 200)
    private String readCodeDesc;

    @Column(name = "READINGDR", length = 64)
    private String readingDr;

    @Column(name = "CONFIRMDR", length = 64)
    private String confirmDr;

    @Column(name = "TRANSCRIPTIONIST", length = 64)
    private String transcriptionist;

    @Column(name = "READINGKEYWORD", length = 128)
    private String readingKeyword;

    @Column(name = "TEACHINGCASED", columnDefinition = "int default 0")
    private int teachingCased;

    @Column(name = "STEREOCOUNT", columnDefinition = "int default 0")
    private int stereoCount;

    @Column(name = "PROTOCOLNAME", length = 256)
    private String protocolName;

    @Column(name = "VIEWPOSITION", length = 255)
    private String viewPosition;

    @Column(name = "LATERALITY", length = 255)
    private String laterality;

    @Column(name = "REASON4STUDY", length = 255)
    private String reasonForStudy;

    @Column(name = "COMMENTS", length = 255)
    private String comments;

    @Column(name = "STUDYTYPE", length = 64)
    private String studyType;

    @Column(name = "ARCHSTATUS", columnDefinition = "int default 0")
    private int archStatus;

    @Column(name = "COMPSTATUS", columnDefinition = "int default 0")
    private int compStatus;

    @Column(name = "DELFLAG", columnDefinition = "int default 0")
    private int delFlag;

    @Column(name = "BACKUPSTATUS", columnDefinition = "int default 0")
    private int backupStatus;

    @Column(name = "BACKUPLABEL", length = 64)
    private String backupLabel;

    @Column(name = "BACKUPDATETIME", length = 16)
    private String backupDateTime;

    @Column(name = "MISMATCHFLAG", columnDefinition = "int default 0")
    private int mismatchFlag;

    @Column(name = "READINGPRIORITY", columnDefinition = "int default 0")
    private int readingPriority;

    @Column(name = "ABNORMALPATIENT", columnDefinition = "int default 0")
    private int abnormalPatient;

    @Column(name = "INSERTDATE", length = 8)
    private String insertDate;

    @Column(name = "INSERTTIME", length = 8)
    private String insertTime;

    @Column(name = "HOSPITALID", columnDefinition = "int default 0")
    private int hospitalId;

    @Column(name = "BURNCNT", columnDefinition = "int default 0")
    private int burnCnt;

    @Column(name = "BURNDATETIME", length = 16)
    private String burnDateTime;

    @Column(name = "VALIDATEFLAG", columnDefinition = "int default 0", nullable = false)
    private int validateFlag;

    @Column(name = "REQREADSTATUS", columnDefinition = "int default 0")
    private int reqReadStatus;

    @Column(name = "TBFLAG", columnDefinition = "int default 0")
    private int tbFlag;

    @Column(name = "MOVIEFLAG", columnDefinition = "int default 0")
    private int movieFlag;

    @Column(name = "OTHERSCPSENDSTATUS", columnDefinition = "int default 0")
    private int otherSCPsendStatus;

    @Column(name = "STATIONFILMROOM", length = 64)
    private String stationFilmRoom;

    @Column(name = "RESERVED1", columnDefinition = "int default 0")
    private int reserved1;

    @Column(name = "RESERVED2", columnDefinition = "int default 0")
    private int reserved2;

    @Column(name = "RESERVED3", columnDefinition = "int default 0")
    private int reserved3;

    @Column(name = "RESERVED4", length = 255)
    private String reserved4;

    @Column(name = "RESERVED5", length = 255)
    private String reserved5;

    @Column(name = "RESERVED6", length = 255)
    private String reserved6;

    @Column(name = "RESERVED7", length = 255)
    private String reserved7;

    @Column(name = "RESERVED8", length = 255)
    private String reserved8;

    @Column(name = "RESERVED9", length = 255)
    private String reserved9;

    @Column(name = "RESERVED10", length = 255)
    private String reserved10;

    @Column(name = "AETITLE", length = 64)
    private String aeTitle;

    @Column(name = "OPENSTATUS", columnDefinition = "int default 0")
    private int openStatus;

    @Column(name = "DAP", columnDefinition = "float")
    private double dap;

    @Column(name = "FILESIZE", columnDefinition = "int default 0")
    private int fileSize;

    @Column(name = "INSERTED", length = 14)
    private String inserted;

    @Column(name = "UPDATED", length = 14)
    private String updated;

    @Column(name = "REMOTE_FLAG", columnDefinition = "int default 0")
    private int remoteFlag;

    @Column(name = "AI_COMPANY", length = 64)
    private String aiCompany;

    @Column(name = "AI_MODEL_NAME", length = 64)
    private String aiModelName;

    @Column(name = "AI_UPDATED", length = 14)
    private String aiUpdated;

    @Column(name = "AI_SCORE", columnDefinition = "float")
    private double aiScore;

    @Column(name = "AI_PRIORITY", columnDefinition = "int default 0")
    private int aiPriority;

    @Column(name = "AI_int_OF_FINDINGS", columnDefinition = "int default 0")
    private int aiIntOfFindings;

    @Column(name = "AI_ABNORMAL_YN", length = 1)
    private String aiAbnormalYn;

    @Column(name = "AI_FINDING", length = 64)
    private String aiFinding;

    @Column(name = "AI_REPORT", length = 4000)
    private String aiReport;

    @Column(name = "AI_VERSION", length = 16)
    private String aiVersion;

    @Column(name = "AI_RESULT_CODE", columnDefinition = "int default 0")
    private int aiResultCode;

    @Column(name = "ACCESS_NUM", length = 255)
    private String accessNum2;

    @Column(name = "ADDED_INFO", length = 255)
    private String addedInfo2;

    @Column(name = "AE_TITLE", length = 255)
    private String aeTitle2;

    @Column(name = "BACKUP_DATE_TIME", length = 255)
    private String backupDateTime2;

    @Column(name = "BACKUP_LABEL", length = 255)
    private String backupLabel2;

    @Column(name = "BODY_PART", length = 255)
    private String bodyPart2;

    @Column(name = "BURN_DATE_TIME", length = 255)
    private String burnDateTime2;

    @Column(name = "CONFIRM_DATE_TIME", length = 255)
    private String confirmDateTime2;

    @Column(name = "CONFIRM_DR", length = 255)
    private String confirmDr2;

    @Column(name = "EXAM_CODE", length = 255)
    private String examCode2;

    @Column(name = "HIS_ADDED_INFO1", length = 255)
    private String hisAddedInfo12;

    @Column(name = "HIS_ADDED_INFO2", length = 255)
    private String hisAddedInfo22;

    @Column(name = "HIS_ADDED_INFO3", length = 255)
    private String hisAddedInfo32;

    @Column(name = "HIS_COMMENTS", length = 255)
    private String hisComments2;

    @Column(name = "INS_NAME", length = 255)
    private String insName2;

    @Column(name = "INSERT_DATE", length = 255)
    private String insertDate2;

    @Column(name = "INSERT_TIME", length = 255)
    private String insertTime2;

    @Column(name = "OPERATORS_NAME", length = 255)
    private String operatorsName2;

    @Column(name = "PAT_AGE", length = 255)
    private String patAge2;

    @Column(name = "PAT_KEY", length = 255)
    private String patKey2;

    @Column(name = "PBIRTH_DATE_TIME", length = 255)
    private String pBirthDateTime2;

    @Column(name = "PERF_PHYSICIAN_NAME", length = 255)
    private String perfPhysicianName2;

    @Column(name = "PFIRST_NAME", length = 255)
    private String pFirstName2;

    @Column(name = "PK_NAME", length = 255)
    private String pKName2;

    @Column(name = "PLAST_NAME", length = 255)
    private String pLastName2;

    @Column(name = "PROTOCOL_NAME", length = 255)
    private String protocolName2;

    @Column(name = "READ_CODE", length = 255)
    private String readCode2;

    @Column(name = "READ_CODE_DESC", length = 255)
    private String readCodeDesc2;

    @Column(name = "READING_DATE_TIME", length = 255)
    private String readingDateTime2;

    @Column(name = "READING_DR", length = 255)
    private String readingDr2;

    @Column(name = "READING_KEYWORD", length = 255)
    private String readingKeyword2;

    @Column(name = "REASON_FOR_STUDY", length = 255)
    private String reasonForStudy2;

    @Column(name = "REF_PHYSICIAN_NAME", length = 255)
    private String refPhysicianName2;

    @Column(name = "REFER_TO_DR_NAME", length = 255)
    private String referToDrName2;

    @Column(name = "REFER_TO_DRID", length = 255)
    private String referToDrId2;

    @Column(name = "REQ_PHYSICIAN_NAME", length = 255)
    private String reqPhysicianName2;

    @Column(name = "SICK_NAME", length = 255)
    private String sickName2;

    @Column(name = "STATION_FILM_ROOM", length = 255)
    private String stationFilmRoom2;

    @Column(name = "STATION_NAME", length = 255)
    private String stationName2;

    @Column(name = "STUDY_DATE", length = 255)
    private String studyDate2;

    @Column(name = "STUDY_DESC", length = 255)
    private String studyDesc2;

    @Column(name = "STUDY_ID", length = 255)
    private String studyId2;

    @Column(name = "STUDY_INS_UID", length = 255)
    private String studyInsUid2;

    @Column(name = "STUDY_TIME", length = 255)
    private String studyTime2;

    @Column(name = "STUDY_TYPE", length = 255)
    private String studyType2;

    @Column(name = "TRANSCRIBED_DATE_TIME", length = 255)
    private String transcribedDateTime2;

    @Column(name = "VERIFY_DATE_TIME", length = 255)
    private String verifyDateTime2;

    @Column(name = "VIEW_POSITION", length = 255)
    private String viewPosition2;

    @Column(name = "ABNORMAL_PATIENT", columnDefinition = "int default 0")
    private int abnormalPatient2;

    @Column(name = "ARCH_STATUS", columnDefinition = "int default 0")
    private int archStatus2;

    @Column(name = "BACKUP_STATUS", columnDefinition = "int default 0")
    private int backupStatus2;

    @Column(name = "BURN_CNT", columnDefinition = "int default 0")
    private int burnCnt2;

    @Column(name = "COMP_STATUS", columnDefinition = "int default 0")
    private int compStatus2;

    @Column(name = "DEL_FLAG", columnDefinition = "int default 0")
    private int delFlag2;

    @Column(name = "EXAM_STATUS", columnDefinition = "int default 0")
    private int examStatus2;

    @Column(name = "FILE_SIZE", columnDefinition = "int default 0")
    private int fileSize2;

    @Column(name = "HOSPITAL_ID", columnDefinition = "int default 0")
    private int hospitalId2;

    @Column(name = "IMAGE_CNT", columnDefinition = "int default 0")
    private int imageCnt2;

    @Column(name = "MISMATCH_FLAG", columnDefinition = "int default 0")
    private int mismatchFlag2;

    @Column(name = "MOVIE_CNT", columnDefinition = "int default 0")
    private int movieCnt2;

    @Column(name = "MOVIE_FLAG", columnDefinition = "int default 0")
    private int movieFlag2;

    @Column(name = "NON_IMAGE_COUNT", columnDefinition = "int default 0")
    private int nonImageCount2;

    @Column(name = "NON_SERIES_COUNT", columnDefinition = "int default 0")
    private int nonSeriesCount2;

    @Column(name = "OPEN_STATUS", columnDefinition = "int default 0")
    private int openStatus2;

    @Column(name = "OTHER_SCP_SEND_STATUS", columnDefinition = "int default 0")
    private int otherScpSendStatus2;

    @Column(name = "PAT_KIND", length = 1)
    private char patKind2;

    @Column(name = "PATIENT_KEY", columnDefinition = "int default 0")
    private int patientKey2;

    @Column(name = "READ_TYPE", columnDefinition = "int default 0")
    private int readType2;

    @Column(name = "READING_PRIORITY", columnDefinition = "int default 0")
    private int readingPriority2;

    @Column(name = "REPORT_STATUS", columnDefinition = "int default 0")
    private int reportStatus2;

    @Column(name = "REQ_READ_STATUS", columnDefinition = "int default 0")
    private int reqReadStatus2;

    @Column(name = "SERIES_CNT", columnDefinition = "int default 0")
    private int seriesCnt2;

    @Column(name = "SERIES_MOVIE_CNT", columnDefinition = "int default 0")
    private int seriesMovieCnt2;

    @Column(name = "STEREO_COUNT", columnDefinition = "int default 0")
    private int stereoCount2;

    @Column(name = "TB_FLAG", columnDefinition = "int default 0")
    private int tbFlag2;

    @Column(name = "TEACHING_CASED", columnDefinition = "int default 0")
    private int teachingCased2;

    @Column(name = "VALIDATE_FLAG", columnDefinition = "int default 0")
    private int validateFlag2;

    @Column(name = "VERIFY_FLAG", columnDefinition = "int default 0")
    private int verifyFlag2;

    @Column(name = "AIABNORMALYN", length = 255)
    private String aiAbnormalYn2;

    @Column(name = "AICOMPANY", length = 255)
    private String aiCompany2;

    @Column(name = "AIFINDING", length = 255)
    private String aiFinding2;

    @Column(name = "AIMODELNAME", length = 255)
    private String aiModelName2;

    @Column(name = "AI_NUMBER_OF_FINDINGS", columnDefinition = "int default 0")
    private int ainumberoffindings;
    
    @Column(name = "AINUMBEROFFINDINGS", columnDefinition = "int default 0")
    private int ainumberoffindings2;

    @Column(name = "AIPRIORITY", columnDefinition = "int default 0")
    private int aiPriority2;

    @Column(name = "AIREPORT", length = 255)
    private String aiReport2;

    @Column(name = "AIRESULTCODE", columnDefinition = "int default 0")
    private int aiResultCode2;

    @Column(name = "AISCORE", columnDefinition = "float")
    private double aiScore2;

    @Column(name = "AIUPDATED", length = 255)
    private String aiUpdated2;

    @Column(name = "AIVERSION", length = 255)
    private String aiVersion2;

    @Column(name = "REASONFORSTUDY", length = 255)
    private String reasonForStudy3;

    @Column(name = "REMOTEFLAG", columnDefinition = "int default 0")
    private int remoteFlag2;

    @Column(name = "TRANSCRIBEDDATE_TIME", length = 255)
    private String transcribedDateTime3;

    @Column(name = "STUDY_KEY", nullable = false)
    private int studyKey2;

    @Column(name = "STORAGECOMPLETION", length = 1)
    private String storageCompletion;
}
