package com.example.gymbo_back_end.order.controller;

import com.example.gymbo_back_end.core.commom.exception.order.OrderNotFoundException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.order.code.OrderErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = OrderController.class)
@Slf4j
public class OrderExceptionController {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResBodyModel> handleOrderNotFoundException(OrderNotFoundException e) {
        log.error("[OrderExceptionController]OrderNotFoundException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(OrderErrorCode.ORDER_NOT_FOUND);

    }
}
