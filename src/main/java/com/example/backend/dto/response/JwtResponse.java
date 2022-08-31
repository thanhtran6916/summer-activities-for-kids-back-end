package com.example.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String username;

    private String token;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date expiresTime;

    private String roles;

    private String message;
}
