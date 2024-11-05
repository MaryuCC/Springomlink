package com.cola.omlink.repository.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("user_info")
@Schema(description = "UserInfo entity")
public class UserInfo extends BaseEntity {
    @Schema(description = "user_id")
    private Long userId;
    @Schema(description = "gender")
    private Integer gender;
    @Schema(description = "pronoun")
    private Integer pronoun;
    @Schema(description = "om_value")
    private Integer om_Value;
    @Schema(description = "credit")
    private Integer credit;
    @Schema(description = "photo")
    private String photo;
    @Schema(description = "position")
    private String myPosition;
    @Schema(description = "university")
    private String university;
    @Schema(description = "profile")
    private String profile;
    @Schema(description = "user_role")
    private Integer userRole;
}
