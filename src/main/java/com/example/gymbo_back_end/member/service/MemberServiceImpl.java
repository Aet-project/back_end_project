package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService{

    private final BCryptPasswordEncoder encoder;
    private final MemberDao memberDao;

    @Override //회원가입
    public Member save(AuthJoinRequestDto authJoinRequestDto) {

        String encode = encoder.encode(authJoinRequestDto.getPassword());

        Member member = Member.builder()
                .memberId(authJoinRequestDto.getMemberId())
                .password(encode)
                .nickName(authJoinRequestDto.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();

      return memberDao.save(member);
    }

    @Override //단일 회원 조회
    public Member find(String memberId) {
        Member member = memberDao.findByMemberId(memberId);
        return member;
    }

    @Override
    public Member find(Long memberSeq) {
        return memberDao.find(memberSeq);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public Member update( MemberRequestDto memberRequestDto) {
        Member member = memberDao.findByMemberId(memberRequestDto.getMemberId());
        member.changeInfo(memberRequestDto);
       return member;
    }

    @Override
    public void delete(String memberId) {
        memberDao.delete(memberId);
    }
}
