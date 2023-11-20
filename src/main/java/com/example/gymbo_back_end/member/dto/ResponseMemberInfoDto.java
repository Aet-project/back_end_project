package com.example.gymbo_back_end.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMemberInfoDto {

    private String memberId;



    private String nickName;

    @Builder
    public ResponseMemberInfoDto(String memberId, String nickName) {
        this.memberId = memberId;
        this.nickName = nickName;
    }
}
