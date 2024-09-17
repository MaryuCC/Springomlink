package com.cola.omlink.manager.service.impl;


import com.cola.omlink.manager.mapper.ProjectMapper;
import com.cola.omlink.manager.service.ProjectService;
import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.entity.product.ProjectDetail;
import com.cola.omlink.repository.vo.Project.ProjectVo;
import com.cola.omlink.utils.AuthContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    // 发布项目
    @Override
    public void createProject(ProjectDto projectDto) {
        // 构建project数据并保存
        Project project = new Project();
        project.setHostId(AuthContextUtil.getUserInfo().getId());
        project.setProjectName(projectDto.getProjectName());
        project.setProjectAddress(projectDto.getProjectAddress());
        project.setProjectPrice(projectDto.getProjectPrice());

        projectMapper.save(project);

        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setProjectId(project.getId());
        projectDetail.setContent(projectDto.getContent());

        projectMapper.saveDetail(projectDetail);

        // TODO 存入佣金

    }

    // 查询项目分页列表
    @Override
    public PageInfo<Project> findProjectByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        // 查询订单信息
        List<Project> projectList = projectMapper.findProjectPage();

        return new PageInfo<>(projectList);
    }

    // 编辑项目信息
    @Override
    public void updateProject(ProjectVo projectVo, Long hostId) {
        Project project = new Project();
        project.setId(projectVo.getProjectId());
        project.setHostId(hostId);
        project.setProjectName(projectVo.getProjectName());
        project.setProjectAddress(projectVo.getProjectAddress());
        project.setProjectPrice(projectVo.getProjectPrice());
        projectMapper.update(project);
        String content = projectVo.getContent();
        projectMapper.updateDetail(project.getId(),content);
    }

    // 删除项目
    @Override
    public void deleteProject(ProjectVo projectVo) {

        if(projectVo != null){
            projectMapper.delete(projectVo.getProjectId());
        }

    }
}
