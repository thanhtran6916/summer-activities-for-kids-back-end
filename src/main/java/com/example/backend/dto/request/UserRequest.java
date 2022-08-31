package com.example.backend.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@Valid
public class UserRequest {

    @NotEmpty(message = "Tên đăng nhập không được để trống!")
    private String username;

    @NotEmpty(message = "Mật khẩu không được để trống!")
    private String password;
}
