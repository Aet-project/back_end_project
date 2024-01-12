package com.example.gymbo_back_end.member.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements BodyCode {
    Member_NOT_FOUND("MEM01", "유저를 찾을 수 없습니다."),
    ;

    private final String message;
    private final String code;

    MemberErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }


}
