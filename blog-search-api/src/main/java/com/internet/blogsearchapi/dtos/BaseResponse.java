package com.internet.blogsearchapi.dtos;

import com.internet.blogsearchcommon.enums.ResultCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse<T> {
    ResultCode resultCode;
    String message;
    T data;
}
