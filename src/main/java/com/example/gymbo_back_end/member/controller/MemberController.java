package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.MemberLoginRequestDto;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<ResBodyModel> join(@RequestBody RequestMemberJoinDto requestMemberJoinDto) {

        Member member = Member.builder()
                .memberId(requestMemberJoinDto.getMemberId())
                .password(requestMemberJoinDto.getPassword())
                .nickName(requestMemberJoinDto.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();

        Member savedMember = memberService.save(member);
        log.info("savedMember = {}",savedMember);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }

    @PostMapping("/login")
    public Optional<TokenInfo> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return Optional.ofNullable(tokenInfo);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }




}
