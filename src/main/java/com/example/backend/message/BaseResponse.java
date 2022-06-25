package com.example.backend.message;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BaseResponse {

    private String message = "OK";

    private String errorCode = "0";

    private Object data;

    private Long totalCount;
}
