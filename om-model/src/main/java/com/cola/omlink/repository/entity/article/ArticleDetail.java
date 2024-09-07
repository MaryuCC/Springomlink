package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("article_detail")
@Schema(description = "ArticleDetail entity")
public class ArticleDetail extends BaseEntity {
    @Schema(description = "article_id")
    private Integer articleId;
    @Schema(description = "version")
    private Integer version;
    @Schema(description = "content")
    private String content;
}
