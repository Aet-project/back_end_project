package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;

import java.util.List;

public interface MemberService {

    Member save(Member member);


    ResponseMemberInfoDto find(String userEmail);

    Member find(Long memberSeq);

    List<ResponseMemberInfoDto> findAll();

    ResponseMemberInfoDto update(RequestMemberJoinDto requestMemberJoinDto);

    void delete(String memberId);

}
