package com.cola.omlink.repository.entity.product;

import com.cola.omlink.repository.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProjectDetail extends BaseEntity {

    private Long projectId;

    private String content;
}
