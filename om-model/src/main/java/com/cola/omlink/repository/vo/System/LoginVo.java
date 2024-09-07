package com.cola.omlink.repository.vo.System;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "login successful response result entity")
public class LoginVo {

    @Schema(description = "token")
    private String token ;

    @Schema(description = "refresh token,can be null")
    private String refresh_token ;

}
