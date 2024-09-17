package com.cola.omlink.repository.vo.Project;


import lombok.Data;

@Data
public class ProjectVo {
    private Long projectId;

    private String projectName;

    private String projectAddress;

    private double projectPrice;

    private String Content;
}
