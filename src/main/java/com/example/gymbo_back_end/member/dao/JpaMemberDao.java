package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaMemberDao implements MemberDao{

    private final MemberRepository memberRepository;
    @Override
    public Member save(Member member) {

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public List<ResponseMemberInfoDto> findAll() {

        List<Member> members = memberRepository.findAll();
        List<ResponseMemberInfoDto> responseMemberInfoDtos = new ArrayList<>();
        for (Member member : members) {
            responseMemberInfoDtos.add(ResponseMemberInfoDto.buildDto(member));
        }
        return responseMemberInfoDtos;
    }
}
