package com.example.gymbo_back_end.ticket.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.ticket.DailyTicket;
import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Slf4j
public class DailyTicketController {

    private final DailyTicketService dailyTicketService;

    @PostMapping
    public ResponseEntity<ResBodyModel> test (@RequestBody DailyTicketRequestDto dailyTicketRequestDto) {

        DailyTicket dailyTicket = dailyTicketService.createdForTest(dailyTicketRequestDto);
        return AetResponse.toResponse(SuccessCode.SUCCESS,dailyTicket);
    }



}
