package com.study.medi.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.study.medi.domain.ImageTab.ImageId;

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
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID", length = 64)
    private String memberId;
    
    @Column(name = "MEMBER_PWD", length = 64)
    private String memberPwd;
	
}
