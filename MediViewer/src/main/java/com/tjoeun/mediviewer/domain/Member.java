package com.tjoeun.mediviewer.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    
    @Column(name = "NAME", length = 64)
    private String name;

    @Column(name = "EMAIL", length = 128)
    private String email;
    
    // 권한 (관리자 : ADMIN / 일반회원 : USER)
    @Column(name = "ROLE", length = 20)
    private String role;
	
}
