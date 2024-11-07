package com.tjoeun.mediviewer.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name="COMMENTTAB")
public class CommentTab {
	
    @Id
    @Column(name = "STUDYKEY")
    private Long studyKey;
    
    @Column(name = "STUDY_COMMENT")
    private String comment;
    
	@LastModifiedDate
	@Column(name="MODIFIED_DATE", columnDefinition="DATE DEFAULT SYSDATE")
	private LocalDateTime modifiedDate;
	
	@CreatedDate
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	
}
