package com.example.gymbo_back_end.auth.controller;

import com.example.gymbo_back_end.auth.service.AuthService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.MemberLoginRequestDto;
import com.example.gymbo_back_end.member.dto.ReissueTokensRequestDto;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/join") //회원 가입
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

    @PostMapping("/login") //로그인
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = authService.login(memberId, password).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return tokenInfo;
    }

    @PostMapping("/re_token")//토큰 재발급 요청
    private TokenInfo reToken(@RequestHeader("refreshToken") String refreshToken,
                                        @RequestBody ReissueTokensRequestDto reissueTokensRequestDto) {
        TokenInfo tokenInfo = authService.reissueTokens(refreshToken, reissueTokensRequestDto);

        return tokenInfo;
    }

}
