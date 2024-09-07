package com.cola.omlink.repository.entity.gift;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("gift_detail")
@Schema(description = "GiftDetail entity")
public class GiftDetail extends BaseEntity {
    @Schema(description = "gift_id")
    private Long giftId;
    @Schema(description = "content")
    private String content;
}
