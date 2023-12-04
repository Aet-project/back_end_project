package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
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

    @Override
    public void delete(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
        memberRepository.delete(member);
    }

    @Override
    public Member find(Long memberSeq) {
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        return member;
    }
}
