package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;

import java.util.List;
import java.util.Optional;

public interface MemberDao {

    Member save(Member member);

    Optional<Member> findByMemberId(String memberId);

    List<ResponseMemberInfoDto> findAll();

    void delete(String memberId) ;
}
