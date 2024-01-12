package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
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
    public Member save(Member member) {

        String encode = encoder.encode(member.getPassword());

        Member saveMember = Member.builder()
                .memberId(member.getMemberId())
                .password(encode)
                .nickName(member.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();
      return memberDao.save(saveMember);
    }

    @Override //단일 회원 조회
    public ResponseMemberInfoDto find(String memberId) {
        Member member = memberDao.findByMemberId(memberId);
        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .build();

        return responseMemberInfoDto;
    }

    @Override
    public Member find(Long memberSeq) {
        return memberDao.find(memberSeq);
    }

    @Override
    public List<ResponseMemberInfoDto> findAll() {
        return memberDao.findAll();
    }

    @Override
    public ResponseMemberInfoDto update( RequestMemberJoinDto requestMemberJoinDto) {
        Member member = memberDao.findByMemberId(requestMemberJoinDto.getMemberId());
        member.changeInfo(requestMemberJoinDto);
       return ResponseMemberInfoDto.buildDto(member);
    }

    @Override
    public void delete(String memberId) {
        memberDao.delete(memberId);
    }
}
