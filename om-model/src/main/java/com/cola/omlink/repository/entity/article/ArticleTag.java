package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("article_tag")
@Schema(description = "ArticleTag entity")
public class ArticleTag extends BaseEntity {
    @Schema(description = "article_id")
    private Long articleId;
    @Schema(description = "tag_id")
    private Long tagId;
}
