package com.cola.omlink.repository.entity.product;

import com.cola.omlink.repository.entity.base.BaseEntity;
import lombok.Data;

@Data
public class Project extends BaseEntity {

    private Long hostId;

    private String projectName;

    private String projectAddress;

    private double projectPrice;

}
