package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("article")
@Schema(description = "Article entity")
public class Article extends BaseEntity {
    @Schema(description = "user_id")
    private Long userId;
    @Schema(description = "article_type")
    private Integer articleType;
    @Schema(description = "title")
    private String title;
    @Schema(description = "short_title")
    private String shortTitle;
    @Schema(description = "picture")
    private String picture;
    @Schema(description = "summary")
    private String summary;
    @Schema(description = "topping__stat")
    private Integer toppingStat;
    @Schema(description = "status")
    private Integer status;
}
