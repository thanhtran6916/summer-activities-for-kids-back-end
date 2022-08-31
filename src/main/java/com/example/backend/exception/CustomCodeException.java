package com.example.backend.exception;

import com.example.backend.message.BaseResponse;
import lombok.Data;

@Data
public class CustomCodeException extends Exception {

    private BaseResponse baseResponse;

    public CustomCodeException(BaseResponse baseResponse) {
        super();
        this.baseResponse = baseResponse;
    }
}
