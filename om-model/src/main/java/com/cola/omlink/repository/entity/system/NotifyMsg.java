package com.cola.omlink.repository.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("notify_msg")
@Schema(description = "NotifyMsg entity")
public class NotifyMsg extends BaseEntity {
    @Schema(description = "related_id")
    private Long relatedId;
    @Schema(description = "notify_user_id")
    private Long notifyUserId;
    @Schema(description = "operate_user_id")
    private Long operateUserId;
    @Schema(description = "msg")
    private String msg;
    @Schema(description = "type")
    private Integer type;
    @Schema(description = "state")
    private Integer state;
}
