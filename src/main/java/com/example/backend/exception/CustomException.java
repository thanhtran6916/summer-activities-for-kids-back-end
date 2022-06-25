package com.example.backend.exception;

import com.example.backend.message.BaseResponse;
import lombok.Data;

@Data
public class CustomException extends Exception {

    private BaseResponse baseResponse;

    public CustomException(BaseResponse baseResponse) {
        super();
        this.baseResponse = baseResponse;
    }
}
