package com.study.medi.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.study.medi.domain.SeriesTab.SeriesId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="PATIENTTAB")
public class PatientTab {

    @Id
    @Column(name = "PID", length = 64)
    private String pid;

    @Column(name = "PNAME", length = 64, nullable = false)
    private String pName;

    @Column(name = "PATKEY", length = 64)
    private String patKey;

    @Column(name = "PATIENTKEY")
    private Long patientKey;

    @Column(name = "PLASTNAME", length = 64)
    private String pLastName;

    @Column(name = "PFIRSTNAME", length = 64)
    private String pFirstName;

    @Column(name = "PSEX", length = 2)
    private String pSex;

    @Column(name = "PBIRTHDATE", length = 8)
    private String pBirthDate;

    @Column(name = "PBIRTHTIME", length = 8)
    private String pBirthTime;

    @Column(name = "COMMENTS", length = 64)
    private String comments;

    @Column(name = "INSERTDATE", length = 8)
    private String insertDate;

    @Column(name = "INSERTTIME", length = 8)
    private String insertTime;

    @Column(name = "HOSPITALID")
    private Integer hospitalId = 0;

    @Column(name = "PKNAME", length = 64)
    private String pKName;

    @Column(name = "PENAME", length = 64)
    private String pEName;

    @Column(name = "INSNAME", length = 255)
    private String insName;

    @Column(name = "DELFLAG")
    private Integer delFlag;

    @Column(name = "INSERTED", length = 14)
    private String inserted;

    @Column(name = "UPDATED", length = 14)
    private String updated;

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
	

}
