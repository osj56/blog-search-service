package com.internet.blogsearchapi.exceptions;

import com.internet.blogsearchapi.dtos.BaseResponse;
import com.internet.blogsearchcommon.enums.ResultCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    ResponseEntity<BaseResponse> handle(RuntimeException e){
        BaseResponse response = BaseResponse.builder()
                .resultCode(ResultCode.FAILED)
                .message(e.getMessage())
                .build();
        return ResponseEntity.ok(response);
    }
}
