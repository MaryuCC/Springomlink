package com.cola.omlink.repository.entity.gift;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("gift")
@Schema(description = "Gift entity")
public class Gift extends BaseEntity {
    @Schema(description = "gift_name")
    private String giftName;
    @Schema(description = "status")
    private Integer status;
}
