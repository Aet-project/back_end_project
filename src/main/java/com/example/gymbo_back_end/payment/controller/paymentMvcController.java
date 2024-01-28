package com.example.gymbo_back_end.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class paymentMvcController {
    @RequestMapping("/toss")
    public String tossPayment(){
        return "/toss.html";
    }

}
