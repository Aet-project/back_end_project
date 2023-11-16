package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;

public interface MemberDao {

    void saveUser(Member member);
}
