package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.MemberPointRequestDto;

public interface MemberPointService {

    MemberPoint memberPointSave(MemberPointRequestDto memberPointRequestDto);

    MemberPoint memberPointFind(String memberId);

    MemberPoint memberPointUpdate(MemberPointRequestDto memberPointRequestDto);
}
