package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.MemberPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.mapper.MemberMapper;
import com.example.gymbo_back_end.member.mapper.MemberPhotoMapper;
import com.example.gymbo_back_end.member.service.MemberPointService;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberPointService memberPointService;
    private final MemberMapper memberMapper;
    private final MemberPhotoMapper memberPhotoMapper;

    @GetMapping("login_view/{memberId}") //로그인시 단일 회원 조회
    public ResponseEntity<ResBodyModel> loginViewRead(@PathVariable String memberId) {
        Member member = memberService.find(memberId);
        Optional<MemberPoint> optionalMemberPointFind = memberPointService.optionalMemberPointFind(memberId);
        ResponseMemberInfoDto responseMemberInfoDto = memberMapper.toResponse(optionalMemberPointFind, member);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

//    @GetMapping("/{memberId}") //단일 회원 조회
//    public ResponseEntity<ResBodyModel> read(@PathVariable String memberId) {
//        Member member = memberService.find(memberId);
//        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
//                .memberId(member.getMemberId())
//                .nickName(member.getNickName())
//                .build();
//        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
//    }

    @GetMapping()//전체 회원 조회
    public ResponseEntity<ResBodyModel> readAll() {
        List<Member> members = memberService.findAll();
        List<ResponseMemberInfoDto> responseMemberInfoDtos = memberMapper.toResponse(members);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDtos);

    }

    @PatchMapping() //회원 정보 수정
    public ResponseEntity<ResBodyModel> update(@RequestBody MemberRequestDto requestMemberJoinDto) {
        Member member = memberService.update(requestMemberJoinDto);
        Optional<MemberPoint> optionalMemberPointFind = memberPointService.optionalMemberPointFind(member.getMemberId());
        ResponseMemberInfoDto responseMemberInfoDto = memberMapper.toResponse(optionalMemberPointFind, member);
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @DeleteMapping("/{memberId}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable String memberId) {
        memberService.delete(memberId);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }

    @DeleteMapping("delete/{memberSeq}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable Long memberSeq) {
        memberService.delete(memberSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }
    @PostMapping("/file_save")// 회원 프로필 저장
    public ResponseEntity<ResBodyModel> memberPhotoSave(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart MemberPhotoRequestDto memberPhotoRequestDto) throws Exception {
        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto,files);
        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
    }

    @PostMapping("/file_update")// 회원 프로필 수정
    public ResponseEntity<ResBodyModel> memberPhotoUpdate(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart MemberPhotoRequestDto memberPhotoRequestDto) throws Exception {
        Member member = memberService.find(memberPhotoRequestDto.getMemberId());
        List<MemberPhoto> memberPhoto = memberService.findMemberPhoto(member.getMemberSeq());

        for (MemberPhoto photo : memberPhoto) {
           memberService.memberPhotoDelete(photo);
        }

        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto,files);
        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
    }

    // 회원 프로필 조회
    @GetMapping(value = "/file_find/{memberSeq}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResBodyModel> memberPhotoFind(@PathVariable("memberSeq") Long memberSeq ) throws Exception {
        List<MemberPhoto> memberPhotos = memberService.findMemberPhoto(memberSeq);
        List<Map<String, Object>> response = memberPhotoMapper.toResponse(memberPhotos);
        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
    }
}
