package com.cola.omlink.repository.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
@Schema(description = "User entity")
public class User extends BaseEntity {
    @Schema(description = "user_name")
    private String userName;
    @Schema(description = "password")
    private String password;
    @Schema(description = "nick_name")
    private String nickName;

}
