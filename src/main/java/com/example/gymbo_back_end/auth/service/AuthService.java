package com.example.gymbo_back_end.auth.service;

import com.example.gymbo_back_end.jwt.TokenInfo;
import com.example.gymbo_back_end.member.dto.ReissueTokensRequestDto;

import java.util.Optional;

public interface AuthService {

    Optional<TokenInfo> login(String memberId, String password);

    TokenInfo reissueTokens(String refreshToken,
                            ReissueTokensRequestDto reissueTokensRequestDto);
}
