package com.example.gymbo_back_end.member.dto.response;

import com.example.gymbo_back_end.core.entity.Member;
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

    public static ResponseMemberInfoDto buildDto (Member member) {
        return ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .build();
    }
}
