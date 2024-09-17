package com.cola.omlink.repository.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200 , "success") ,
    LOGIN_ERROR(201 , "username does not exist"),
    PWD_ERROR(2011,"wrong password"),
    VALIDATECODE_ERROR(202 , "wrong validate code") ,
    LOGIN_AUTH(208 , "The user is not logged in."),
    USER_NAME_IS_EXISTS(209 , "The username already exists"),
    SYSTEM_ERROR(9999 , "There seems to be a network issue. Please try again later."),
    NODE_ERROR( 217, "This node has child nodes and cannot be deleted."),
    DATA_ERROR(204, "Data error"),
    ACCOUNT_STOP( 216, "The account is inactive."),
    ORDER_ERROR(218,"cannot accept your own order"),

    STOCK_LESS( 219, "库存不足"),
    NOT_FOUND(404,"Not Found")

    ;

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
