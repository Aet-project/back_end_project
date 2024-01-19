package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.gym.handler.GymFileHandler;
import com.example.gymbo_back_end.member.controller.MemberRoles;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.member.dto.MemberPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.handler.MemberFileHandler;
import com.example.gymbo_back_end.member.repository.MemberPhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService{

    private final BCryptPasswordEncoder encoder;
    private final MemberDao memberDao;
    private final MemberFileHandler memberFileHandler;
    private final MemberPhotoRepository memberPhotoRepository;

    @Override //회원가입
    public Member save(AuthJoinRequestDto authJoinRequestDto) {

        String encode = encoder.encode(authJoinRequestDto.getPassword());

        Member member = Member.builder()
                .memberId(authJoinRequestDto.getMemberId())
                .password(encode)
                .nickName(authJoinRequestDto.getNickName())
                .roles(Collections.singletonList(MemberRoles.USER.getRole()))
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

    @Override //회원 이미지 저장
    public List<MemberPhoto> saveMemberPhoto(MemberPhotoRequestDto memberPhotoRequestDto,List<MultipartFile> files) throws Exception{

        List<MemberPhoto> memberPhotos = memberFileHandler.parseFileInfo(files);
        Member member = memberDao.findByMemberId(memberPhotoRequestDto.getMemberId());
        for (MemberPhoto memberPhoto : memberPhotos) {
            memberPhotoRepository.save(memberPhoto);
            memberPhoto.addMember(member);
        }
        return memberPhotos;
    }
}
