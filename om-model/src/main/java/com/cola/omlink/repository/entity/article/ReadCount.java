package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("read_count")
@Schema(description = "ReadCount entity")
public class ReadCount extends BaseEntity {
    @Schema(description = "document_id")
    private Long documentId;
    @Schema(description = "document_type")
    private Integer documentType;
    @Schema(description = "cnt")
    private Integer cnt;
}
