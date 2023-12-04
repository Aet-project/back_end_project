package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member save(Member member);

    Optional<TokenInfo> login(String memberId, String password);

    ResponseMemberInfoDto find(String userEmail);

    Member find(Long memberSeq);

    List<ResponseMemberInfoDto> findAll();

    ResponseMemberInfoDto update(RequestMemberJoinDto requestMemberJoinDto);

    void delete(String memberId);

}
