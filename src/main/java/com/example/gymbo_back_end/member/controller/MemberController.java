package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.MemberLoginRequestDto;
import com.example.gymbo_back_end.member.dto.ReissueTokensRequestDto;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

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
    public Optional<TokenInfo> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
        return Optional.ofNullable(tokenInfo);
    }

    @PostMapping("/re_token")
    private Optional<TokenInfo> reToken(@RequestHeader("refreshToken") String refreshToken,
                                        @RequestBody ReissueTokensRequestDto reissueTokensRequestDto) {
        TokenInfo tokenInfo = memberService.reissueTokens(refreshToken, reissueTokensRequestDto);

        return Optional.ofNullable(tokenInfo);
    }

    @GetMapping("/{memberId}") //단일 회원 조회
    public ResponseEntity<ResBodyModel> read (@PathVariable String memberId) {
        ResponseMemberInfoDto responseMemberInfoDto = memberService.find(memberId);
       return AetResponse.toResponse(SuccessCode.SUCCESS,responseMemberInfoDto);
    }

    @GetMapping()//전체 회원 조회
    public ResponseEntity<ResBodyModel> readAll() {
        List<ResponseMemberInfoDto> members = memberService.findAll();

        return AetResponse.toResponse(SuccessCode.SUCCESS,members);

    }

    @PatchMapping() //회원 정보 수정
    public ResponseEntity<ResBodyModel> update( @RequestBody RequestMemberJoinDto requestMemberJoinDto) {
        ResponseMemberInfoDto updateMember = memberService.update( requestMemberJoinDto);

        return AetResponse.toResponse(SuccessCode.SUCCESS,updateMember);
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
