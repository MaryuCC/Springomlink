package com.cola.omlink.repository.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cola.omlink.repository.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("user_foot")
@Schema(description = "UserFoot entity")
public class UserFoot extends BaseEntity {
    @Schema(description = "user_id")
    private Long userId;
    @Schema(description = "document_id")
    private Long documentId;
    @Schema(description = "document_type")
    private Integer documentType;
    @Schema(description = "document_user_id")
    private Long documentUserId;
    @Schema(description = "collection_stat")
    private Integer collectionStat;
    @Schema(description = "read_stat")
    private Integer readStat;
    @Schema(description = "comment_stat")
    private Integer commentStat;
    @Schema(description = "upvote_stat")
    private Integer upvoteStat;
}
