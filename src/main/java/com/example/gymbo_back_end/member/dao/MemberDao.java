package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;

import java.util.Optional;

public interface MemberDao {

    Member save(Member member);

    Optional<Member> findByMemberId(String memberId);
}
