package com.example.gymbo_back_end.gym.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.repository.GymPhotoRepository;
import com.example.gymbo_back_end.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/gyms")
@RequiredArgsConstructor
public class GymController {


    public final GymService gymService;
    public final GymPhotoRepository gymPhotoRepository;
    private static final String UPLOAD_DIR = "images";

    @PostMapping("/save") //운동 시설 등록
    public ResponseEntity<ResBodyModel> gymRegistration(@RequestBody GymSaveRequestDto gymSaveRequestDto) {

        log.info("gymSaveRequestDto ={}",gymSaveRequestDto);
        log.info("gymSaveRequestDtoManNumber = {}",gymSaveRequestDto.getManagerNumber());


        Gym gym = gymService.save(gymSaveRequestDto);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gym);

    }

    @PostMapping("/file_save") //운동시설 사진 등록
    public ResponseEntity<ResBodyModel> gymPhotoSave(@RequestPart(required = false)  List<MultipartFile> files
            ,@RequestPart GymPhotoRequestDto gymPhotoRequestDto ) throws Exception {
        List<GymPhoto> gymPhotos = gymService.saveGymPhoto(gymPhotoRequestDto, files);



        return AetResponse.toResponse(SuccessCode.SUCCESS,gymPhotos);
    }

    @PostMapping("/file_update") // 운동 시설 사진 업데이트
    public ResponseEntity<ResBodyModel> gymPhotoUpdate(@RequestPart(required = false) List<MultipartFile> files
            ,@RequestPart GymPhotoRequestDto gymPhotoRequestDto) throws Exception {

        List<GymPhoto> gymPhoto = gymService.findGymPhoto(gymPhotoRequestDto.getGymNumber());
        for (GymPhoto photo : gymPhoto) {
            gymPhotoRepository.delete(photo);
        }

        List<GymPhoto> gymPhotos = gymService.saveGymPhoto(gymPhotoRequestDto, files);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymPhotos);
    }

    @GetMapping(value = "/file/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}) //사진 번호로 사진 조회
    public ResponseEntity<ResBodyModel> fileFind(@PathVariable Long id) throws IOException {

        GymPhoto gymPhoto = gymService.findByGymPhotoSeq(id);

         /*
        new File(""): 기본적으로 현재 작업 디렉터리를 참조하는 빈 문자열로 새 File 객체를 생성합니다.
        .getAbsolutePath(): 현재 작업 디렉터리의 절대 경로인 File 개체의 절대 경로 이름을 검색합니다.
        + File.separator + File.separator: 획득한 절대 경로를 파일 구분 기호와 연결합니다.
        File.separator를 두 번 사용하는 이유는 경로와 후속 파일 또는 디렉터리 이름 사이에 올바른 파일 구분 기호가 추가되었는지 확인하기 위한 것입니다
        */
        String absolutePath
                = new File("").getAbsolutePath() + File.separator + File.separator;

        String path = gymPhoto.getFilePath();

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return AetResponse.toResponse(SuccessCode.SUCCESS,imageByteArray);
    }

    @GetMapping(value = "/files/{gym_seq}", produces = {MediaType.APPLICATION_JSON_VALUE}) // 운동시설로 사진 조회
    public ResponseEntity<ResBodyModel> getAllImages(@PathVariable Long gym_seq) throws IOException {
        Gym gym = gymService.find(gym_seq);
        List<GymPhoto> gymPhotos = gymPhotoRepository.findGymPhotosByGym(gym);
        List<Map<String, Object>> imageList = new ArrayList<>();

        for (GymPhoto gymPhoto : gymPhotos) {

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = gymPhoto.getFilePath();
            InputStream imageStream = new FileInputStream(absolutePath + path);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();

            // 이미지 바이트 배열을 Base64로 인코딩하여 문자열로 변환
            String base64EncodedImage = Base64.encodeBase64String(imageByteArray);

            Map<String, Object> imageInfo = new HashMap<>();
            imageInfo.put("fileName", gymPhoto.getOrigFileName());
            imageInfo.put("imageBytes", base64EncodedImage);
            imageList.add(imageInfo);
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS, imageList);
    }
    @GetMapping("/{gymName}") //운동시설 이름으로 조회
    public ResponseEntity<ResBodyModel> findByGymName ( @PathVariable String gymName) {

        log.info("gymName = {}",gymName);

        Gym gym = gymService.find(gymName);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    @GetMapping("/all") // 운동시설 전체 조회
    public ResponseEntity<ResBodyModel> findAllGym () {

        List<Gym> gyms = gymService.findAll();
        List<GymResponseDto> gymResponseDtoList = new ArrayList<>();
        for (Gym gym : gyms) {
            GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
            gymResponseDtoList.add(gymResponseDto);
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDtoList);
    }

    @PatchMapping //운동시설 업데이트
    private ResponseEntity<ResBodyModel> updateGym(@RequestBody GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymService.update(gymSaveRequestDto);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

}
