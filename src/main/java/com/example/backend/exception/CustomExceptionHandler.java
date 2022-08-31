package com.example.backend.exception;

import com.example.backend.message.BaseResponse;
import com.example.backend.message.ErrorMessage;
import com.example.backend.message.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomCodeException.class)
    protected BaseResponse customCodeException(CustomCodeException custom) {
        return custom.getBaseResponse();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setHttpStatus(status);
        errorModel.setTimestamp(LocalDateTime.now(ZoneOffset.ofHours(7)));

        ErrorMessage errorMessage = null;
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessage = ErrorMessage.builder()
                    .fieldName(fieldError.getField())
                    .errorMessage(fieldError.getDefaultMessage())
                    .build();

            errorModel.getDetails().add(errorMessage);
        }

        BaseResponse baseResponse = BaseResponse.builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(HttpStatus.BAD_REQUEST.name())
                .data(errorModel.getDetails())
                .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }
}
