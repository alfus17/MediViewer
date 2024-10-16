package com.tjoeun.mediviewer.domain;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="IMAGETAB")
public class ImageTab {

	// 이미지탭 db 
    @Embeddable
    @Data
    public static class ImageId implements Serializable {
        @Column(name = "STUDYKEY")
        private Long studyKey;

        @Column(name = "SERIESKEY")
        private Long seriesKey;

        @Column(name = "IMAGEKEY")
        private Long imageKey;

        public ImageId() {}

        public ImageId(Long studyKey, Long seriesKey, Long imageKey) {
            this.studyKey = studyKey;
            this.seriesKey = seriesKey;
            this.imageKey = imageKey;
        }
    }

    @EmbeddedId
    private ImageId id;

    @Column(name = "STUDYINSUID", length = 64, nullable = false)
    private String studyInsUid;

    @Column(name = "SERIESINSUID", length = 64, nullable = false)
    private String seriesInsUid;

    @Column(name = "SOPINSTANCEUID", length = 64, nullable = false)
    private String sopInstanceUid;

    @Column(name = "SOPCLASSUID", length = 64)
    private String sopClassUid;

    @Column(name = "PATH", length = 256)
    private String path;

    @Column(name = "FNAME", length = 256)
    private String fname;

    @Column(name = "SERIESNUMBER")
    private Integer seriesNumber;

    @Column(name = "INSTANCENUM", length = 16)
    private String instanceNum;

    @Column(name = "CURSEQNUM")
    private Integer curSeqNum;

    @Column(name = "WINDOW")
    private Integer window;

    @Column(name = "LEV")
    private Integer lev;

    @Column(name = "CONTENTDATE", length = 8)
    private String contentDate;

    @Column(name = "CONTENTTIME", length = 16)
    private String contentTime;

    @Column(name = "ACQDATE", length = 8)
    private String acqDate;

    @Column(name = "ACQTIME", length = 16)
    private String acqTime;

    @Column(name = "STUDYID", length = 64)
    private String studyId;

    @Column(name = "VIEWPOSITION", length = 255)
    private String viewPosition;

    @Column(name = "LATERALITY", length = 64)
    private String laterality;

    @Column(name = "IMAGETYPE", length = 256)
    private String imageType;

    @Column(name = "FMXDATA", length = 255)
    private String fmxData;

    @Column(name = "IMAGECOMMENTS", length = 255)
    private String imageComments;

    @Column(name = "ADDITIONALDESC", length = 32)
    private String additionalDesc;

    @Column(name = "IMAGEORIENTATION", length = 256)
    private String imageOrientation;

    @Column(name = "IMAGEPOSITION", length = 256)
    private String imagePosition;

    @Column(name = "PIXELSPACING", length = 256)
    private String pixelSpacing;

    @Column(name = "PIXELROWS")
    private Integer pixelRows;

    @Column(name = "PIXELCOLUMNS")
    private Integer pixelColumns;

    @Column(name = "BITSALLOCATED")
    private Integer bitsAllocated;

    @Column(name = "SPECIFICCHARACTERSET", length = 64)
    private String specificCharacterSet;

    @Column(name = "TRANSFERSYNTAXUID", length = 64)
    private String transferSyntaxUid;

    @Column(name = "SOURCEAPPLICATIONENTITYTITLE", length = 16)
    private String sourceApplicationEntityTitle;

    @Column(name = "LOSSYIMAGECOMPRESSION", length = 64)
    private String lossyImageCompression;

    @Column(name = "SAMPLEPERPIXEL")
    private Integer samplePerPixel;

    @Column(name = "PHOTOMETRICINTERPRETATION", length = 16)
    private String photometricInterpretation;

    @Column(name = "BITSSTORED")
    private Integer bitsStored;

    @Column(name = "HIGHBIT")
    private Integer highBit;

    @Column(name = "PIXELREPRESENTATION")
    private Integer pixelRepresentation;

    @Column(name = "PLANARCONFIGURATION")
    private Integer planarConfiguration;

    @Column(name = "FRAMECNT")
    private Integer frameCnt;

    @Column(name = "GEOMSTATUS")
    private Integer geomStatus;

    @Column(name = "ARCHSTATUS")
    private Integer archStatus;

    @Column(name = "ARCHPATH", length = 255)
    private String archPath;

    @Column(name = "DELFLAG")
    private Integer delFlag;

    @Column(name = "VERIFYFLAG")
    private Integer verifyFlag;

    @Column(name = "HIDEFLAG")
    private Integer hideFlag;

    @Column(name = "KEYFLAG")
    private Integer keyFlag;

    @Column(name = "COMPSTATUS")
    private Integer compStatus;

    @Column(name = "PRESENTATIONSTATEDATA", length = 255)
    private String presentationStateData;

    @Column(name = "SHARPENVALUE")
    private Integer sharpenValue;

    @Column(name = "LUTDATA", length = 255)
    private String lutData;

    @Column(name = "IMAGESIZE")
    private Integer imageSize;

    @Column(name = "COMPSIZE")
    private Integer compSize;

    @Column(name = "MOVPATH", length = 255)
    private String movPath;

    @Column(name = "MOVFNAME", length = 255)
    private String movFname;

    @Column(name = "MOVIEFLAG")
    private Integer movieFlag;

    @Column(name = "CODECTYPE", length = 128)
    private String codecType;

    @Column(name = "FRAMERATE")
    private Float frameRate;

    @Column(name = "FRAMETIME")
    private Float frameTime;

    @Column(name = "RECSTARTDATE", length = 8)
    private String recStartDate;

    @Column(name = "RECSTARTTIME", length = 6)
    private String recStartTime;

    @Column(name = "RECENDDATE", length = 8)
    private String recEndDate;

    @Column(name = "RECENDTIME", length = 6)
    private String recEndTime;

    @Column(name = "MOVSTSTORAGEID")
    private Integer movStStorageId;

    @Column(name = "LTSTORAGEID", columnDefinition = "integer default 0")
    private Integer ltStorageId;

    @Column(name = "STSTORAGEID")
    private Integer stStorageId;

    @Column(name = "WEBSTORAGEID")
    private Integer webStorageId;

    @Column(name = "INSERTDATE", length = 8)
    private String insertDate;

    @Column(name = "INSERTTIME", length = 8)
    private String insertTime;

    @Column(name = "INSERTED", length = 14)
    private String inserted;

    @Column(name = "UPDATED", length = 14)
    private String updated;

    @Column(name = "HOSPITALID", columnDefinition = "integer default 0")
    private Integer hospitalId;

    @Column(name = "RESERVED1")
    private Integer reserved1;

    @Column(name = "RESERVED2")
    private Integer reserved2;

    @Column(name = "RESERVED3")
    private Integer reserved3;

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

    @Column(name = "PHOTOMETRIC")
    private Integer photometric;

    @Column(name = "PATIENTORIENTATION", length = 64)
    private String patientOrientation;

    @Column(name = "PRESENTATIONLUTSHAPE", length = 16)
    private String presentationLutShape;

    @Column(name = "INSTANCECREATIONDATE", length = 8)
    private String instanceCreationDate;

    @Column(name = "INSTANCECREATIONTIME", length = 14)
    private String instanceCreationTime;

    @Column(name = "SOURCEAETITLE", length = 64)
    private String sourceAeTitle;

    @Column(name = "AI_SCORE")
    private Integer aiScore;

    @Column(name = "AI_FINDING_COUNT")
    private Integer aiFindingCount;

    @Column(name = "REPORTSTATUS", length = 255)
    private String reportStatus;
}
