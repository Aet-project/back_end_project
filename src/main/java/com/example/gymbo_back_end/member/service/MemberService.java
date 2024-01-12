package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;

import java.util.List;

public interface MemberService {

    Member save(AuthJoinRequestDto authJoinRequestDto);


    Member find(String userEmail);

    Member find(Long memberSeq);

    List<Member> findAll();

    Member update(MemberRequestDto memberRequestDto);

    void delete(String memberId);

}
