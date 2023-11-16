package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;

import java.util.Optional;

public interface MemberService {

    Member save(Member member);

    Optional<TokenInfo> login(String memberId, String password);
}
