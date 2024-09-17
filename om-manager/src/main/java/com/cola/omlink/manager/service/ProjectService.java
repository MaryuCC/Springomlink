package com.cola.omlink.manager.service;

import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.vo.Project.ProjectVo;
import com.github.pagehelper.PageInfo;

public interface ProjectService {

    // 发布项目
    void createProject(ProjectDto projectDto);

    // 查询项目分页列表
    PageInfo<Project> findProjectByPage(Integer page, Integer limit);

    void updateProject(ProjectVo projectVo, Long hostId);

    void deleteProject(ProjectVo projectVo);
}
