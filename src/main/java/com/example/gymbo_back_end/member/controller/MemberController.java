package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping("delete/{memberSeq}") //회원 삭제
    public ResponseEntity<ResBodyModel> delete(@PathVariable Long memberSeq) {
        memberService.delete(memberSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS);
    }
    @PostMapping("/file_save")
    public ResponseEntity<ResBodyModel> memberPhotoSave(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart MemberPhotoRequestDto memberPhotoRequestDto) throws Exception {

        List<MemberPhoto> memberPhotos = memberService.saveMemberPhoto(memberPhotoRequestDto,files);
        return AetResponse.toResponse(SuccessCode.SUCCESS,memberPhotos);
    }

    @GetMapping(value = "/file_find/{memberSeq}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResBodyModel> memberPhotoFind(@PathVariable("memberSeq") Long memberSeq ) throws Exception {
        List<MemberPhoto> memberPhotos = memberService.findMemberPhoto(memberSeq);
        List<Map<String,Object>> imageList = new ArrayList<>();

        for (MemberPhoto memberPhoto : memberPhotos) {
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = memberPhoto.getFilePath();
            InputStream imageStream = new FileInputStream(absolutePath + path);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();

            String base64EncodedImage = Base64.encodeBase64String(imageByteArray);

            Map<String, Object> imageInfo = new HashMap<>();
            imageInfo.put("fileName",memberPhoto.getOrigFileName());
            imageInfo.put("imageBytes",base64EncodedImage);
            imageList.add(imageInfo);
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS,imageList);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }


}
