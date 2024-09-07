package com.cola.omlink.repository.entity.article;

import com.cola.omlink.repository.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Project extends BaseEntity {

    private String projectName;

    private String projectAddress;

    private double projectPrice;

}
