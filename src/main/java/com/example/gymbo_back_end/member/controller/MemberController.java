package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}") //단일 회원 조회
    public ResponseEntity<ResBodyModel> read(@PathVariable String memberId) {
        Member member = memberService.find(memberId);
        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .build();
        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @GetMapping()//전체 회원 조회
    public ResponseEntity<ResBodyModel> readAll() {
        List<Member> members = memberService.findAll();

        List<ResponseMemberInfoDto> responseMemberInfoDtos = new ArrayList<>();
        for (Member member : members) {
            responseMemberInfoDtos.add(ResponseMemberInfoDto.buildDto(member));
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDtos);

    }

    @PatchMapping() //회원 정보 수정
    public ResponseEntity<ResBodyModel> update(@RequestBody MemberRequestDto requestMemberJoinDto) {
        Member member = memberService.update(requestMemberJoinDto);
        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.buildDto(member);

        return AetResponse.toResponse(SuccessCode.SUCCESS, responseMemberInfoDto);
    }

    @DeleteMapping("/{memberId}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable String memberId) {
        memberService.delete(memberId);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }


    @PostMapping("/test")
    public String test() {
        return "success";
    }


}
