package com.cola.omlink.repository.entity.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("comment")
@Schema(description = "Comment entity")
public class Comment extends BaseEntity {
    @Schema(description = "article_id")
    private Long articleId;
    @Schema(description = "user_id")
    private Long userId;
    @Schema(description = "content")
    private String content;
    @Schema(description = "top_comment_id")
    private Long topCommentId;
    @Schema(description = "parent_comment_id")
    private Long parentCommentId;
}
