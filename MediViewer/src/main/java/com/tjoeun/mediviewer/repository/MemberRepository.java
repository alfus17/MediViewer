package com.tjoeun.mediviewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tjoeun.mediviewer.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByMemberId(String memberId);
}