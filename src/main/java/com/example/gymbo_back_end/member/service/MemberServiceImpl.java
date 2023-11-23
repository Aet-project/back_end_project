package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.jwt.JwtTokenProvider;
import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dao.JpaMemberDao;
import com.example.gymbo_back_end.member.dto.RequestMemberJoinDto;
import com.example.gymbo_back_end.member.dto.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService{

    private final BCryptPasswordEncoder encoder;
    private final JpaMemberDao jpaMemberDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Member save(Member member) {

        String encode = encoder.encode(member.getPassword());

        Member saveMember = Member.builder()
                .memberId(member.getMemberId())
                .password(encode)
                .nickName(member.getNickName())
                .roles(Collections.singletonList("USER"))
                .build();
      return jpaMemberDao.save(saveMember);
    }

    @Transactional
    public Optional<TokenInfo> login(String memberId, String password) {

        Member member = jpaMemberDao.findByMemberId(memberId).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));


        if (encoder.matches(password,member.getPassword())==true) { //비밀번호가 암호화된 비민번호와 일치한지 확인

            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, member.getPassword());

            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

            return Optional.ofNullable(tokenInfo);
        } else {
            return null;
        }
    }

    @Override
    public ResponseMemberInfoDto find(String memberId) {
        Member member = jpaMemberDao.findByMemberId(memberId).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .build();

        return responseMemberInfoDto;
    }

    @Override
    public List<ResponseMemberInfoDto> findAll() {
        return jpaMemberDao.findAll();
    }

    @Override
    public ResponseMemberInfoDto update( RequestMemberJoinDto requestMemberJoinDto) {
        Member member = jpaMemberDao.findByMemberId(requestMemberJoinDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 회원입니다."));
        member.changeInfo(requestMemberJoinDto);
       return ResponseMemberInfoDto.buildDto(member);
    }

    @Override
    public void delete(String memberId) {
        jpaMemberDao.delete(memberId);
    }
}
