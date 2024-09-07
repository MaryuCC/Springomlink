package com.cola.omlink.repository.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user_relation")
@Schema(description = "UserRelation entity")
public class UserRelation extends BaseEntity {
    @Schema(description = "user_id")
    private Long userId;
    @Schema(description = "follow_user_id")
    private Long followUserId;
    @Schema(description = "follow_state 0-unfollow  1-following 2-cancel follow")
    private Integer followState;

}
