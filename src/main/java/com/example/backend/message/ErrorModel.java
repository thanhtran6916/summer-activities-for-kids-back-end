package com.example.backend.message;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorModel {
    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private List<ErrorMessage> details = new ArrayList<>();

}
