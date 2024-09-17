package com.cola.omlink.manager.controller;


import com.cola.omlink.manager.service.ProjectService;
import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.vo.Project.ProjectVo;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.cola.omlink.utils.AuthContextUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 发布项目
    @PostMapping("auth/createProject")
    public Result createProject(@PathVariable ProjectDto projectDto){
        projectService.createProject(projectDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 查询项目分页列表
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<Project>> list(@PathVariable Integer page,
                                          @PathVariable Integer limit
                                          //@RequestParam(required = false, defaultValue = "") Integer type
                                        ){
        PageInfo<Project> pageInfo = projectService.findProjectByPage(page,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 编辑项目信息
    @PutMapping("auth/updateProject")
    public Result updateProject(@RequestBody ProjectVo projectVo){
        projectService.updateProject(projectVo, AuthContextUtil.getUserInfo().getId());
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    // 删除项目
    @DeleteMapping("auth/deleteProject")
    public Result deleteProject(@RequestBody ProjectVo projectVo){
        projectService.deleteProject(projectVo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }




}
