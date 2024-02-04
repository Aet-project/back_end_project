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

    private Long point;

    @Builder
    public ResponseMemberInfoDto(String memberId, String nickName,Long point) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.point = point;
    }

    public static ResponseMemberInfoDto buildDto (Member member,Long point) {
        return ResponseMemberInfoDto.builder()
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .point(point)
                .build();
    }
}
