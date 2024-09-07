package com.cola.omlink.repository.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("request_count")
@Schema(description = "RequestCount entity")
public class RequestCount extends BaseEntity {
    @Schema(description = "host")
    private String host;
    @Schema(description = "cnt")
    private Integer cnt;
    @Schema(description = "date")
    private Object date;
}
