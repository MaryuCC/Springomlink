package com.cola.omlink.repository.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseEntity implements Serializable {
    @TableId
    @Schema(description = "Primary key ID")
    private Long id;

    @Schema(description = "Creation time")
    private Timestamp createTime;

    @Schema(description = "Last update time")
    private Timestamp updateTime;

    @Schema(description = "Logical deletion flag")
    private Byte deleted;
}
