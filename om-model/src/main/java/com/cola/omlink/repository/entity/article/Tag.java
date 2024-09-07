package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("tag")
@Schema(description = "Tag entity")
public class Tag extends BaseEntity {
    @Schema(description = "tag_name")
    private String tagName;
    @Schema(description = "tag_type")
    private Integer tagType;
    @Schema(description = "status")
    private Integer status;
}
